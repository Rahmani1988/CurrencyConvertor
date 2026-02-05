import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.buildkonfig)
}

buildkonfig {
    packageName = "org.reza.currency.shared"

    // Default configuration (used for all builds)
    defaultConfigs {
        buildConfigField(type = FieldSpec.Type.STRING, name = "BASE_URL", value = "https://v6.exchangerate-api.com/v6/")
    }

    // Optional: Override for debug builds
    targetConfigs {
        create("debug") {
            buildConfigField(type = FieldSpec.Type.STRING, name = "BASE_URL", value = "https://v6.exchangerate-api.com/v6/")
        }
    }
}


kotlin {
    androidTarget()
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp) // Engine for Android
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin) // Engine for iOS
        }
    }
}

android {
    namespace = "org.reza.currency.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}