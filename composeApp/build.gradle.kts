import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.serialization)
    id("app.cash.sqldelight") version "2.0.1"
}

sqldelight {
    databases {
        create("SmartDatabase") {
            packageName.set("smartwaste.rubbish")
        }
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm("desktop") {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.okhttp)

            implementation(libs.koin.android)

            implementation(libs.compose.ui)
            implementation(libs.compose.android.material)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            implementation("app.cash.sqldelight:android-driver:2.0.1")
        }
        commonMain.dependencies {

            implementation(libs.napier)
            implementation(libs.bundles.koin.multiplatform)
            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.bundles.ktor.multiplatform)
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.settings.multiplatform)

            implementation(libs.kotlinx.collections.immutable)

            implementation("app.cash.sqldelight:coroutines-extensions:2.0.1")

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.uiTooling)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(libs.window.size)

            implementation(libs.bundles.voyaer.multiplatform)
        }

        desktopMain.dependencies {
            implementation(compose.preview)
            implementation(libs.bundles.ktor.multiplatform.jvm)
            implementation(compose.desktop.currentOs)

            runtimeOnly(libs.kotlinx.coroutines.swing)

            implementation("app.cash.sqldelight:sqlite-driver:2.0.1")
        }
    }
}

android {
    namespace = "ua.smartwaste.kmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].res.srcDirs("src/commonMain/resources")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "ua.smartwaste.kmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "ua.smartwaste.kmp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ua.smartwaste.kmp"
            packageVersion = "1.0.0"
        }
    }
}
