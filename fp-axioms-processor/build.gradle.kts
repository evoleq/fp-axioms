plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "org.evoleq"
version = "0.0.3"

dependencies {
    implementation(project(":fp-axioms-definitions"))
    implementation(libs.ksp.api)
    implementation(libs.kotlinpoet)
    implementation(libs.kotlinpoet.ksp)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifactId = "fp-axioms-processor"
        }
    }
}
