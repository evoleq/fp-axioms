plugins {
    // Hier definieren wir nur die Versionen, ohne sie auf das Root-Projekt anzuwenden
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.ksp) apply false
    id("com.android.library") version "8.2.0" apply false

    `maven-publish`
}

allprojects {
    group = "org.evoleq"
    version = "0.0.2"

    repositories {
        mavenCentral()
        google()
        mavenLocal()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx/dev") }
        gradlePluginPortal()
    }
}

// Hilfs-Task zum Aufräumen
tasks.register<Delete>("cleanup") {
    delete(rootProject.layout.buildDirectory)
}


/*
plugins {
    kotlin("jvm") version "1.9.24"
    id("com.google.devtools.ksp") version "1.9.24-1.0.20"
    `java-gradle-plugin`
    `java-library`
    `maven-publish`
}


group = "org.evoleq"
version = "0.0.2"

repositories {
    mavenCentral()
    google()
}

dependencies {
    // gradle api
    implementation(gradleApi()) // Gradle API for plugin development

    implementation(kotlin("stdlib"))
    // ksp-processing
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.24-1.0.20")
    implementation("com.squareup:kotlinpoet:1.14.2")// for generating code
    implementation("com.squareup:kotlinpoet-ksp:1.14.2")

    // test stuff
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("com.google.devtools.ksp:symbol-processing-api:1.9.24-1.0.20")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

java {
    withSourcesJar()
}

tasks.register<Jar>("fatJar") {
    group = "build"
    archiveClassifier.set("all")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

gradlePlugin {
    plugins {
        create("fpAxiomsPlugin") {
            id = "org.evoleq.fp-axioms-plugin"
            implementationClass = "org.evoleq.axioms.plugin.FpAxiomsPlugin" // Your plugin's implementation class
        }
    }
}

tasks.register("generateVersion") {
    val versionFile = file("src/main/resources/version.txt")
    doLast {
        versionFile.writeText(project.version.toString())
    }
}

tasks.named("processResources") {
    dependsOn("generateVersion")
}


publishing {
    publications {
        create<MavenPublication>("kspProcessor") {
            from(components["java"])
            groupId = "${project.group}" // Change this to your package structure
            artifactId = "fp-axioms"
            version = "${project.version}"
        }
        create<MavenPublication>("kspPlugin") {
            from(components["java"])
            groupId = "${project.group}" // Change this to your package structure
            artifactId = "fp-axioms-plugin"
            version = "${project.version}"
        }
    }

    repositories {
        mavenLocal() // This targets ~/.m2/repository
    }
}

 */

