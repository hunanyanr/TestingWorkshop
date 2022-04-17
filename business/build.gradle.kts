plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion
}


//enables context receivers
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

buckets(coroutinesBucket, networkBucket, functionalBucket, unitTestingBucket)