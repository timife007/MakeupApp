plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = ConfigData.compileSdkVersion
    defaultConfig {
        applicationId = "com.timife.makeup"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["runnerBuilder"] = "de.mannodermaus.junit5.AndroidJUnit5Builder"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        val release by getting{
            isMinifyEnabled = false
            proguardFiles (
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
        val benchmark by creating{
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks.add("release")
            isDebuggable = false
        }
    }
    productFlavors {
        create("dev"){
        }
    }
    applicationVariants.all{
        val ext = when(flavorName){
            "dev" -> ".devUrlExt"
            else -> null
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    testOptions {
        unitTests.all {
//            useJUnitPlatform()
        }
    }
    packagingOptions {
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/license.txt")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("META-INF/notice.txt")
        resources.excludes.add("META-INF/ASL2.0")
        resources.excludes.add("META-INF/*.kotlin_module")
    }
    lint {
        baseline = file("lint-baseline.xml")
    }
    lintOptions {
        disable("JvmStaticProvidesInObjectDetector", "FieldSiteTargetOnQualifierAnnotation", "ModuleCompanionObjects", "ModuleCompanionObjectsNotInModuleParent")
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:cache"))
    implementation(project(":core:remote"))

    implementation(project(":domain"))

    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation (Deps.materialDesign)
    implementation (Deps.constraintLayout)



    implementation(Deps.legacy)


    //di
    implementation(Deps.hilt)
    testImplementation("junit:junit:4.12")
    kapt(Deps.hiltAndroidCompiler)
    kapt (Deps.hiltCompiler)
    implementation(Deps.hiltNavigation)

    //Nav Component
    implementation (Deps.navigationFragment)
    implementation (Deps.navigationUi)

    //Room
    implementation (Deps.room)
    implementation (Deps.roomKtx)
    kapt (Deps.roomCompiler)

    //ViewModel
    implementation (Deps.livedata)

    //Coroutines
    implementation (Deps.coroutineCore)
    implementation (Deps.coroutinesAndroid)
    implementation (Deps.coroutinesPlay)



    implementation (Deps.lifecycle)

    implementation (Deps.glide)

    annotationProcessor (Deps.glideCompiler)

    // MAKE LIVE-DATA FIRE EVENTS ONLY ONCE
    implementation (Deps.liveEvent)

    //Logging
    implementation (Deps.timber)

    //Tests
    testImplementation (Deps.coroutinesTest)
    testImplementation (Deps.jupiterParams)
    testRuntimeOnly (Deps.jupiterApi)
    testImplementation (Deps.jupiterEngine)
    testImplementation (Deps.junitTest)
    testImplementation (Deps.coreTest)
    testRuntimeOnly (Deps.vintage)
    testImplementation (Deps.truth)
    testImplementation (Deps.turbine)
    testImplementation (Deps.mockk)

    //Instrumentation Tests
    androidTestImplementation ("org.junit.jupiter:junit-jupiter")
    androidTestImplementation(Deps.j5TestCore)
    androidTestRuntimeOnly(Deps.j5TestRunner)
    androidTestImplementation (Deps.espresso)
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation (Deps.truth)
    androidTestImplementation (Deps.coroutinesTest)
    androidTestImplementation ("androidx.arch.core:core-testing:2.1.0")
}