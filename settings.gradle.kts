pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "MakeUp"
include (":app")
include (":core")
include (":core:data")
include (":core:cache")
include (":core:remote")
include (":domain")
include (":benchmark")
include(":build-logic")
include(":build-logic:convention")
