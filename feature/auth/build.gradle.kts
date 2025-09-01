plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.androidkmp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
}

kotlin {
    androidLibrary {
        namespace = "com.cerebus.auth"
        compileSdk = 36
    }
    val xcfName = "authKit"

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
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.compose.icons)
            implementation(libs.androidx.navigation.compose)
            implementation(project(":core:network"))
        }


        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(compose.uiTooling)
            //TODO Надо ли?
            implementation(libs.koin.android.compose)
        }

        iosMain.dependencies {

        }

    }

}