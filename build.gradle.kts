val coroutinesVersion: String by project
val junitVersion: String by project
val assertjVersion: String by project
val kotestVersion: String by project

plugins {
    kotlin("jvm") version "2.3.0"
    application
}

application {
    mainClass.set("lotto.ApplicationKt")
}

group = "camp.nextstep.edu"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
}

tasks {
    test {
        useJUnitPlatform()
    }
    named<JavaExec>("run") {
        standardInput = System.`in`
    }
}