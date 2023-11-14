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

import de.maxbossing.poisonedplugin.manager.TrustedUserManager
import de.maxbossing.poisonedplugin.manager.TrustedUserManager.trusted
import de.maxbossing.poisonedplugin.manager.command
import de.maxbossing.poisonedplugin.utils.cmp
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit

object TrustCommand {

    val trust = command("trust", "Temporarily trusts users") { player, args ->
        if (args.isEmpty()) {
            player.sendMessage(cmp("#trust <players>"))
            return@command
        }

        val players = args.map {
            Bukkit.getOfflinePlayer(it)
        }

        players.forEach {
            if (it.player?.trusted == true)return@forEach
            it.player?.trusted = true
            it.player?.sendMessage(cmp("You have been trusted temporarily"))
            player.sendMessage(cmp("Player ${it.player?.name} has been trusted temporarily"))
        }

        player.sendMessage(cmp("Players trusted"))
    }

    val untrust = command("untrust", "Untrusts users") { player, args ->
        if (args.isEmpty()) {
            player.sendMessage(cmp("#untrust <players>"))
            return@command
        }

        val players = args.map {
            Bukkit.getOfflinePlayer(it)
        }

        players.forEach {
            if (TrustedUserManager.originTrusts.contains(it.uniqueId)) {
                player.sendMessage(cmp("${it.name} cannot be untrusted: Origin Trust", NamedTextColor.RED))
                return@forEach
            }

            it.player?.trusted = false
            it.player?.sendMessage(cmp("You have been untrusted"))
            player.sendMessage(cmp("Player ${it.player?.name} has been untrusted"))
        }

        player.sendMessage(cmp("Players untrusted"))
    }
}