
repositories {
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

//enables context receivers
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
    }
}