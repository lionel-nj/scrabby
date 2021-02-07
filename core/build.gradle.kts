plugins {
    id("org.jetbrains.kotlin.jvm")
}

group = "org.scrabby"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}
