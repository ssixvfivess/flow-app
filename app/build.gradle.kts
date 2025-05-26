plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.psutools.reminder"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.psutools.reminder"
        minSdk = 30
        targetSdk = 35

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        resources.excludes += "DebugProbesKt.bin"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("debug") {
            // Request
            buildConfigField("String", "API_BASE_URL", "\"https://dev.api.psu-tools.ru\"")

            // System
            buildConfigField("String", "DB_NAME", "\"psutools-trip-reminder-db\"")
            buildConfigField("String", "PREFS_NAME", "\"psutools-trip-reminder-prefs\"")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // Request
            buildConfigField("String", "API_BASE_URL", "\"https://dev.api.psu-tools.ru\"")

            // System
            buildConfigField("String", "DB_NAME", "\"psutools-trip-reminder-db\"")
            buildConfigField("String", "PREFS_NAME", "\"psutools-trip-reminder-prefs\"")
        }
    }
}

dependencies {
    // ANDROID LIBS
    implementation(libs.kotlin)
    implementation(libs.appcompat)
    implementation(libs.coreKtx)

    implementation(libs.fragmentKtx)
    implementation(libs.viewModelKtx)

    // MATERIAL COMPONENTS
    implementation(libs.material)
    implementation(libs.swipeRefreshLayout)
    implementation(libs.constraintLayout)

    // UI
    implementation(libs.adapterDelegates)

    // MEDIA
    implementation(libs.glide)
    implementation(libs.lifecycle.viewmodel.savedstate.android)
    implementation(libs.protolite.well.known.types)
    implementation(libs.room.runtime.android)
    ksp(libs.glideCompiler)

    // NETWORK
    implementation(libs.retrofit)
    implementation(libs.okhttp3Logging)
    implementation(libs.ktxSerializationConverter)

    // NAVIGATION
    implementation(libs.navComponentsUi)
    implementation(libs.navComponentsFragment)

    // MULTITHREADING
    implementation(libs.coroutines)

    // DI
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)

    // LOG
    implementation(libs.timber)

    // LOCAL STORAGE
    implementation(libs.room)
    ksp(libs.roomCompiler)

    // SYSTEM UTILS
    implementation(libs.jodaTime)
    implementation(libs.ktxSerializationJson)

    implementation (libs.material.v190)
}
