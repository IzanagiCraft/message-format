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
    mavenLocal()
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

publishing {
    (components["java"] as AdhocComponentWithVariants).withVariantsFromConfiguration(configurations["shadowRuntimeElements"]) {
        skip()
    }

    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])

            pom {
                name.set(project.name)
                url.set("https://github.com/IzanagiCraft/message-format")
                properties.put("inceptionYear", "2023")

                licenses {
                    license {
                        name.set("General Public License (GPL v3.0)")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.txt")
                        distribution.set("repo")
                    }
                }

                developers {
                    developer {
                        id.set("sanguine6660")
                        name.set("Sanguine")
                        email.set("sanguine6660@gmail.com")
                        url.set("https://github.com/sanguine6660")
                    }
                }
            }
        }
    }

    repositories {
        mavenLocal()

        if (System.getProperty("publishName") != null && System.getProperty("publishPassword") != null) {
            maven("https://artifactory.izanagicraft.tech/repository/maven-snapshots/") {
                this.name = "artifactory-izanagicraft-snapshots"
                credentials {
                    this.password = System.getProperty("publishPassword")
                    this.username = System.getProperty("publishName")
                }
            }
        }
    }
}

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

    processTestResources {
        exclude("**/lang.properties")
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
