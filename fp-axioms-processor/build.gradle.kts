plugins {
    kotlin("jvm")
    `maven-publish`
}

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
