plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.androidkmp)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {

    androidLibrary {
        namespace = "com.cerebus.network"
        compileSdk = 36
    }

    val xcfName = "coreKit"

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = xcfName
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.serialization.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.coil.network.ktor)
            implementation(libs.kotlinx.coroutines.core)
        }


        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }


        androidMain.dependencies {
            // Add Android-specific dependencies here. Note that this source set depends on
            // commonMain by default and will correctly pull the Android artifacts of any KMP
            // dependencies declared in commonMain.
            implementation(libs.ktor.client.okhttp)
        }



        iosMain.dependencies {
            // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
            // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
            // part of KMP’s default source set hierarchy. Note that this source set depends
            // on common by default and will correctly pull the iOS artifacts of any
            // KMP dependencies declared in commonMain.
            implementation(libs.ktor.client.darwin)
        }
    }

}