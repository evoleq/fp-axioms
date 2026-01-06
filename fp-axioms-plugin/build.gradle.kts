plugins {
    kotlin("jvm")
    `java-gradle-plugin`
    `maven-publish`
}

group = "org.evoleq"
version = "0.0.4"

dependencies {
    compileOnly(gradleApi())
    compileOnly(libs.kotlin.gradle.plugin)

    implementation(kotlin("stdlib"))
}

gradlePlugin {
    plugins {
        create("fpAxiomsPlugin") {
            id = "org.evoleq.fp-axioms-plugin"
            implementationClass = "org.evoleq.axioms.plugin.FpAxiomsPlugin"
        }
    }
}

// Version für das Plugin zur Laufzeit verfügbar machen
tasks.register("generateVersion") {
    val versionFile = file("src/main/resources/version.txt")
    doLast {
        versionFile.parentFile.mkdirs()
        versionFile.writeText(project.version.toString())
    }
}
tasks.named("processResources") { dependsOn("generateVersion") }