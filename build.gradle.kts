plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation("org.jsoup:jsoup:1.16.1")
}

tasks.shadowJar {
    archiveBaseName.set("4chan-scrape")
    manifest {
        attributes["Main-Class"] = "org.example.Main"
    }
    destinationDirectory.set(file("${project.rootDir}"))
}


tasks.test {
    useJUnitPlatform()
}