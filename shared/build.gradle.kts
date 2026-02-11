import com.codingfeline.buildkonfig.compiler.FieldSpec
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.buildkonfig)
}

val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) {
        load(file.inputStream())
    }
}

val apiKey = localProperties.getProperty("EXCHANGE_API_KEY") ?: ""

buildkonfig {
    packageName = "org.reza.currency.shared"

    // Default configuration (used for all builds)
    defaultConfigs {
        buildConfigField(type = FieldSpec.Type.STRING, name = "BASE_URL", value = "https://v6.exchangerate-api.com/v6/")
        buildConfigField(FieldSpec.Type.STRING, "EXCHANGE_API_KEY", apiKey)
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
            // Network
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            // DI (Core + Compose Integration)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel) // Needed for koinViewModel()

            // Lifecycle (ViewModel support in commonMain)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp) // Engine for Android
            implementation(libs.koin.android)
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