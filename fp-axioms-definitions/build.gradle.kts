plugins {
    kotlin("multiplatform")
    id("com.android.library")
    `maven-publish`
}

group = "org.evoleq"
version = "0.0.3"

kotlin {
    jvm()
    js(IR) { browser() }
    applyDefaultHierarchyTemplate()
    androidTarget{
        publishLibraryVariants("release")
    }
    // Weitere Targets wie ios(), native() können hier ergänzt werden

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
    }
}


android {
    // WICHTIG: Namespace passend zu deinem Paket wählen
    namespace = "org.evoleq.axioms.definitions"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

publishing {
    publications {
        // Das KMP Plugin erstellt automatisch Publications für Android und JVM
    }
}
