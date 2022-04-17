plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
}

android {
    compileSdk = AndroidSdk.compile
    namespace = AppDefaultConfig.applicationId
    defaultConfig {
        applicationId = AppDefaultConfig.applicationId
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = AppDefaultConfig.appVersionCode
        versionName = AppDefaultConfig.versionName
        testInstrumentationRunner = AppDefaultConfig.instrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xcontext-receivers")
    }

    buildFeatures {
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

//inject all the dependencies
buckets(androidXBucket, coroutinesBucket, unitTestingBucket, diBucket)

dependencies {
    implementation(project(mapOf("path" to ":business")))
}
