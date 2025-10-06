plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.androidkmp)
}

kotlin {

    androidLibrary {
        namespace = "com.cerebus.utils"
        compileSdk = 36
        minSdk = 29
    }

    val xcfName = "coreUtilsKit"

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
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.kermit)
                implementation(libs.koin.core)
            }
        }

        androidMain {
            dependencies { }
        }

        iosMain {
            dependencies { }
        }
    }

}