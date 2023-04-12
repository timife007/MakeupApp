plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = ConfigData. compileSdkVersion
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
//    productFlavors {
//        create("dev"){
//            val dimension = this.dimension.nameAsSafeName("dev").toString()
//        }
//    }
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
        this.jvmTarget = "11"
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

    implementation(libs.androidx.core.ktx)
    implementation (libs.android.material)
    implementation (libs.androidx.constraint)
    add("implementation",libs.androidx.core.ktx)



    implementation(libs.androidx.legacy)


    //di
    implementation(libs.hilt.android)
    testImplementation(libs.junit.test)
    kapt(libs.hilt.android.compiler)
    kapt (libs.hilt.compiler)
    implementation(libs.hilt.navigation)

    //Nav Component
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)

    //Room
    implementation (libs.room.runtime)
    implementation (libs.room.ktx)
    kapt (libs.room.compiler)

    //ViewModel
    implementation (libs.androidx.livedata)

    //Coroutines
    implementation (libs.coroutine.core)
    implementation (libs.coroutine.android)
    implementation (libs.coroutine.play)



    implementation (libs.androidx.lifecycle)

    implementation (libs.glide)

    annotationProcessor (libs.glide.compiler)

    // MAKE LIVE-DATA FIRE EVENTS ONLY ONCE
    implementation (libs.live.event)

    //Logging
    implementation (libs.timber)

    //Tests
    testImplementation (libs.coroutine.test)
    testImplementation (libs.jupiter.params)
    testRuntimeOnly (libs.jupiter.api)
    testImplementation (libs.jupiter.engine)
    testImplementation (libs.junit.test)
    testImplementation (libs.androidx.core.test)
    testRuntimeOnly (libs.vintage)
    testImplementation (libs.truth)
    testImplementation (libs.turbine)
    testImplementation (libs.mockk)

    //Instrumentation Tests
    androidTestImplementation ("org.junit.jupiter:junit-jupiter")
    androidTestImplementation(libs.junit5.test.core)
    androidTestRuntimeOnly(libs.junit5.test.runner)
    androidTestImplementation (libs.androidx.espresso)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation (libs.junit.ext)
    androidTestImplementation (libs.truth)
    androidTestImplementation (libs.coroutine.test)
    androidTestImplementation (libs.androidx.core.test)
}