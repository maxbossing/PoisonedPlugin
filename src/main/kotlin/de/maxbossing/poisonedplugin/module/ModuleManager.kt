package de.maxbossing.poisonedplugin.module

import de.maxbossing.poisonedplugin.module.coreprotect.CoreProtectModule
import de.maxbossing.poisonedplugin.module.luckperms.LuckPermsModule
import de.maxbossing.poisonedplugin.module.worldguard.WorldGuardModule
import de.maxbossing.poisonedplugin.utils.log
import org.bukkit.Bukkit

/**
 * Initializes Modules that depend on other Plugins/Systems like CoreProtect
 */
object ModuleManager {
    init {

        // CoreProtect hider
        if (Bukkit.getPluginManager().isPluginEnabled("CoreProtect")) {
            CoreProtectModule
            log("CoreProtect Module enabled")
        }

        // Luckperms Permission manipulation
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            LuckPermsModule
            log("LuckPerms Module enabled")
        }

        // Worldguard bypasses
        if (Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
            WorldGuardModule
            log("WorldGuard Module enabled")
        }
    }
}