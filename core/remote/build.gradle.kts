plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles ("consumer-rules.pro")
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = false
            proguardFiles (
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation (Deps.coreKtx)
    implementation (Deps.appCompat)
    testImplementation (Deps.junitTest)
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    //di
    implementation(Deps.hilt)
    kapt(Deps.hiltAndroidCompiler)
    kapt (Deps.hiltCompiler)

    //retrofit
    implementation (Deps.retrofit)
    implementation (Deps.moshi)
    implementation (Deps.coroutinesAdapter)

    implementation (Deps.lifecycle)

    //leak canary to detect memory leaks
    debugImplementation (Deps.canary)

    // Chucker for analysing network traffic
    debugImplementation (Deps.chuckerLib)
    releaseImplementation (Deps.chuckerNoOp)
}