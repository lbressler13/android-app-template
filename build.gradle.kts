plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.ktlint) apply false
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}
