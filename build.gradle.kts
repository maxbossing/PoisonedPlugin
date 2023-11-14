/*
 * MIT License
 *
 * Copyright (c) 2023 Max Bossing
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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