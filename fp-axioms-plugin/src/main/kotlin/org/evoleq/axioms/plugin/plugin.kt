package org.evoleq.axioms.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FpAxiomsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val version = this::class.java
            .classLoader
            .getResource("version.txt")
            ?.readText()
            ?.trim()
            ?: "0.0.2" // Fallback, falls version.txt fehlt

        // KSP Plugin automatisch anwenden
        project.plugins.apply("com.google.devtools.ksp")

        project.afterEvaluate {
            // use the following after switch to sonatype.
            // till the use jitpack and the jitpack group
            // val definitionsDep = "org.evoleq:fp-axioms-definitions:$version"
            // val processorDep = "org.evoleq:fp-axioms-processor:$version"
            val jitpackGroup = "com.github.evoleq.fp-axioms"
            val definitionsDep = "$jitpackGroup:fp-axioms-definitions:$version"
            val processorDep = "$jitpackGroup:fp-axioms-processor:$version"

            val mppExtension = project.extensions.findByType(KotlinMultiplatformExtension::class.java)

            if (mppExtension != null) {
                // MULTIPLATFORM
                // 1. Annotationen zum commonMain hinzufügen (damit sie überall verfügbar sind)
                mppExtension.sourceSets.findByName("commonMain")?.dependencies {
                    implementation(definitionsDep)
                }

                // 2. Prozessor zu allen KSP-Konfigurationen (kspJvm, kspJs, etc.) hinzufügen
                project.configurations.forEach { config ->
                    if (config.name.startsWith("ksp") && !config.name.contains("Test", ignoreCase = true)) {
                        project.dependencies.add(config.name, processorDep)
                    }
                }
            } else {
                // SINGLE PLATFORM (JVM)
                // 1. Annotationen für den Compile-Classpath
                project.dependencies.add("implementation", definitionsDep)
                // 2. Prozessor für KSP
                project.dependencies.add("ksp", processorDep)
            }
        }
    }
}