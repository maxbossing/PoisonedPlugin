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