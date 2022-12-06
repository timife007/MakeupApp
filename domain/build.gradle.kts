plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("kotlin-parcelize")
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
        release {
            isMinifyEnabled =  false
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
    implementation (Deps.materialDesign)
    testImplementation (Deps.junitTest)
    androidTestImplementation(Deps.junitExt)
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    testImplementation (Deps.truth)
    testImplementation (Deps.turbine)
    testImplementation (Deps.coroutinesTest)

    //di
    implementation(Deps.hilt)
    kapt(Deps.hiltAndroidCompiler)
    kapt (Deps.hiltCompiler)
}