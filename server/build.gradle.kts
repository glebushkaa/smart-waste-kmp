plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "ua.gleb.smartwaste"
version = "1.0.0"

application {
    mainClass.set("ua.gleb.smartwaste.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.bundles.ktor.server)
    implementation(libs.logback.classic)
    implementation(libs.h2.database)
    implementation(libs.bundles.exposed)
}