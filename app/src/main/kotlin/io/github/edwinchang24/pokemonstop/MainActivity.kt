package io.github.edwinchang24.pokemonstop

import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        setContent {
            MaterialTheme {
                var state by rememberSaveable { mutableIntStateOf(0) }
                when (state) {
                    0 -> {
                        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                            Image(painterResource(id = R.drawable.splash_screen), contentDescription = null)
                        }
                        LaunchedEffect(true) {
                            delay(2000)
                            windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
                            delay(500)
                            state = 1
                        }
                    }
                    1 -> {
                        Spacer(modifier = Modifier.fillMaxSize().background(Color.Black))
                        LaunchedEffect(true) {
                            WindowCompat.setDecorFitsSystemWindows(window, true)
                            delay(1000)
                            state = 2
                        }
                    }
                    2 -> {
                        Surface(modifier = Modifier.fillMaxSize().background(Color.Black)) {
                            Spacer(modifier = Modifier.background(Color.Black).fillMaxWidth().height(5.dp))
                            AndroidView(
                                modifier = Modifier.padding(top = 5.dp),
                                factory = { context ->
                                    VideoView(context).apply {
                                        setVideoPath("android.resource://$packageName/${R.raw.pokemon_stop}")
                                        start()
                                        setOnCompletionListener { finish() }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
