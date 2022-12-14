// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    dependencies{
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
    }
}
plugins {
    id ("com.android.application") version "7.2.1" apply false
    id ("com.android.library") version "7.2.1" apply false
    id ("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id ("com.google.dagger.hilt.android") version "2.41" apply false
    id ("com.android.test") version "7.2.1" apply false
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}