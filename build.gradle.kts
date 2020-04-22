@file:Suppress("SuspiciousCollectionReassignment")

import com.github.lamba92.gradle.utils.kotlinx
import com.github.lamba92.gradle.utils.prepareForPublication
import com.github.lamba92.gradle.utils.serialization

buildscript {
    repositories {
        maven("https://dl.bintray.com/lamba92/com.github.lamba92")
        google()
    }
    dependencies {
        classpath("com.github.lamba92", "lamba-gradle-utils", "1.0.6")
    }
}

plugins {
    kotlin("jvm") version "1.4-M1"
    kotlin("plugin.serialization") version "1.4-M1"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
}

group = "com.github.lamba92"
version = "1.0.0"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://dl.bintray.com/lamba92/com.github.lamba92")
    maven("https://dl.bintray.com/kotlin/kotlinx.html")
    jcenter()
    mavenCentral()
}

kotlin.target.compilations.all {
    kotlinOptions {
        jvmTarget ="1.8"
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}

dependencies {

    val kotlinxSerializationVersion: String by project
    val coroutinesVersion: String by project

    implementation(kotlin("stdlib-jdk8"))
    implementation(serialization("runtime", kotlinxSerializationVersion))
    implementation(kotlinx("coroutines-core", coroutinesVersion))
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

val mainPublication by publishing.publications.creating(MavenPublication::class) {
    from(components["java"])
    artifact(sourcesJar)
}

prepareForPublication()
