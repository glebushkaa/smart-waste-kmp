plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqldelight)
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
    jvm()


    sourceSets {

        val mobileMain by creating {
            dependsOn(commonMain.get())
        }
        androidMain.get().dependsOn(mobileMain)

        mobileMain.dependencies {
            implementation(libs.bundles.ktor.multiplatform)
            implementation(libs.settings.multiplatform)

            implementation(libs.sqldelight.coroutines.extension)
        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.okhttp)

            api(libs.koin.android)

            implementation(libs.sqldelight.android.driver)
        }

        commonMain.dependencies {
            implementation(libs.napier)
            api(libs.bundles.koin.multiplatform)
            implementation(libs.kotlinx.coroutines.core)


            implementation(libs.kotlinx.serialization.core)
            api(libs.kotlinx.collections.immutable)
        }
    }
}

android {
    namespace = "ua.gleb.smartwaste.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
