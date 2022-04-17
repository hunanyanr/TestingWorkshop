// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.diffplug.spotless") version "5.10.0"
}

buildscript {
    val compose_version by extra("1.0.1")
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20-M1")
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        maven(url = "https://kotlin.bintray.com/kotlinx/")
    }

    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")

            ktlint(ktlintVersion)
            licenseHeaderFile(rootProject.file("picsart/copyright.kt"))
        }
    }
}
