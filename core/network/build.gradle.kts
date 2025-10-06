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

    val xcfName = "coreNetworkKit"

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
            implementation(libs.ktor.client.okhttp)
        }



        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }

}