plugins {
    kotlin("jvm") version "2.3.0"
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
    testImplementation("org.junit.jupiter", "junit-jupiter", "5.14.3")
    testImplementation("org.assertj", "assertj-core", "3.27.7")
    testRuntimeOnly("org.junit.platform", "junit-platform-launcher")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
