import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "${project.property("group")}"
version = "${project.property("version")}"

repositories {
    mavenCentral()

    maven(url = "https://jitpack.io")
}

dependencies {
    // Minestom CE
    implementation("dev.hollowcube:minestom-ce:${project.property("minestom")}")

    // Logback
    implementation("ch.qos.logback:logback-classic:${project.property("logback")}")

    // Minimessage
    implementation("net.kyori:adventure-text-minimessage:${project.property("minimessage")}")
}

tasks {
    application {
        mainClass.set("dev.josemc.islands.Bootstrap")
    }

    withType<ShadowJar>() {
        archiveFileName.set("Islands-${version}.jar")
    }
}

