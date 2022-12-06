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
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
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
    implementation(project(":domain"))
    implementation(project(":core:cache"))
    implementation(project(":core:remote"))


    implementation (Deps.coreKtx)
    implementation (Deps.appCompat)
    implementation (Deps.materialDesign)
    testImplementation (Deps.junitTest)
    testImplementation (Deps.turbine)
    testImplementation (Deps.mockk)
    testImplementation (Deps.coroutinesTest)
    testImplementation (Deps.truth)
    androidTestImplementation ("androidx.test.ext:junit:1.1.4")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.0")

    //di
    implementation(Deps.hilt)
    kapt(Deps.hiltAndroidCompiler)
    kapt (Deps.hiltCompiler)

    implementation( Deps.retrofit)



    //Logging
    implementation (Deps.timber)
}