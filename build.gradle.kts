@Suppress("DSL_SCOPE_VIOLATION") plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinter) apply true
}
true
