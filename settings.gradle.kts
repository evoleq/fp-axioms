rootProject.name = "fp-axioms"
include("fp-axioms-definitions")
include("fp-axioms-processor")
include("fp-axioms-plugin")

pluginManagement {
    repositories {
        google()
        mavenLocal()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx/dev")
    }
    plugins {
        id("com.google.devtools.ksp") version "2.1.0-1.0.29"
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

