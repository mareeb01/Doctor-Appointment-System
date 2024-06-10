pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven { url = uri("https://storage.googleapis.com/r8-releases/raw") }
        maven { url = uri ("https://jitpack.io") }
    }
}

rootProject.name = "Doctor Appointment System"
include(":app")
