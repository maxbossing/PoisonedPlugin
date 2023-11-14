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
package de.maxbossing.poisonedplugin.command

import de.maxbossing.poisonedplugin.manager.command
import de.maxbossing.poisonedplugin.utils.cmp
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Location

object TeleportCommand {
    val teleportCommand = command("teleport", "Teleports you to a sepcified location or player", listOf("tp")) { player, args ->
        if (args.isEmpty()){
            player.sendMessage(cmp("#tp <player | <x> <y> <z>>", NamedTextColor.RED))
            return@command
        }
        if (args.size == 1) {
            val target = Bukkit.getOfflinePlayer(args[0])

            if (target.player == null) {
                player.sendMessage(cmp("Player not found!", NamedTextColor.RED))
                return@command
            }

            if (!target.isOnline) {
                player.sendMessage(cmp("Player not online!", NamedTextColor.RED))
                return@command
            } else {
                player.teleport(target.player!!.location)
            }
        } else if (args.size == 3) {
            if (args[0].toDoubleOrNull() == null || args[1].toDoubleOrNull() == null || args[2].toDoubleOrNull() == null) {
                player.sendMessage(cmp("#tp <player | <x> <y> <z>>", NamedTextColor.RED))
                return@command
            }
            player.teleport(Location(player.world, args[0].toDouble(), args[1].toDouble(), args[2].toDouble()))
        }
    }
}