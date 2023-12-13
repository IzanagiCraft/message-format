import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.cadixdev.gradle.licenser.LicenseExtension

plugins {
    id("java")
    id("java-library")
    id("maven-publish")
    id("signing")

    alias(libs.plugins.shadow)
    alias(libs.plugins.licenser)

    eclipse
    idea
}

group = "com.izanagicraft.messages"
version = "1.0-SNAPSHOT"
description = "A Simple Message Translation and Placeholder replacement lib."

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.compileJava.configure {
    options.encoding = Charsets.UTF_8.name()
    options.release.set(17)
}

configurations.all {
    attributes.attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 17)
}

configure<LicenseExtension> {
    header(rootProject.file("HEADER.txt"))
    include("**/*.java")
    newLine.set(true)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    withSourcesJar()
    withJavadocJar()
}

// TODO: publishing to maven repo

tasks {

    withType<ProcessResources> {
        filesMatching("*") {
            expand(project.properties)
        }
    }

    compileJava {
        options.compilerArgs.addAll(arrayOf("-Xmaxerrs", "1000"))
        options.compilerArgs.add("-Xlint:all")
        for (disabledLint in arrayOf("processing", "path", "fallthrough", "serial")) options.compilerArgs.add("-Xlint:$disabledLint")
        options.isDeprecation = true
        options.encoding = Charsets.UTF_8.name()
    }

    jar {
        this.archiveClassifier.set(null as String?)
        this.archiveFileName.set("${project.name}-${project.version}-unshaded.${this.archiveExtension.getOrElse("jar")}")
        this.destinationDirectory.set(file("$projectDir/out/unshaded"))
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }

    named("build") {
        dependsOn(named("shadowJar"))
    }
}

tasks.named<ShadowJar>("shadowJar") {
    this.archiveClassifier.set(null as String?)
    this.archiveFileName.set("${project.name}-${project.version}.${this.archiveExtension.getOrElse("jar")}")
    this.destinationDirectory.set(file("$projectDir/out"))
    // Get rid of all the libs which are 100% unused.
    minimize()
    mergeServiceFiles()
}