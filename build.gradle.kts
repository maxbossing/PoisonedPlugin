plugins {
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.serialization") version "1.9.10"

    id("io.papermc.paperweight.userdev") version "1.5.9"
    id("xyz.jpenilla.run-paper") version "2.2.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "de.maxbossing"
version = 1

repositories {
    mavenCentral()
    maven { url = uri("https://maven.playpro.com") }
    maven { url = uri("https://maven.enginehub.org/repo/") }
}

dependencies {
    paperweight.paperDevBundle("1.20.2-R0.1-SNAPSHOT")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // Softdepends for modules
    compileOnly("net.coreprotect", "coreprotect", "22.2")
    compileOnly("net.luckperms", "api", "5.4")
    compileOnly("com.sk89q.worldguard", "worldguard-bukkit", "7.0.9")
}

kotlin {
    jvmToolchain(17)
}

tasks {
    assemble {
        dependsOn(reobfJar)
        dependsOn(shadowJar)
    }
    runServer {
        this.minecraftVersion("1.20.2")
    }
}

bukkit {
    name = "PoisonedPlugin"
    author = "Max Bossing <info@maxbossing.de>"
    main = "de.maxbossing.poisonedplugin.PoisonedPlugin"
    apiVersion = "1.20"

    softDepend = listOf(
        "CoreProtect",
        "LuckPerms",
        "WorldGuard"
    )

    libraries = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib:1.9.20"
    )
}