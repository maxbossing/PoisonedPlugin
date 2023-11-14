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
import de.maxbossing.poisonedplugin.utils.plus
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import kotlin.math.roundToInt

object CoordsCommand {
    private fun getCoords(player: OfflinePlayer): List<Component> {
        val current =
            cmp("Current: ", NamedTextColor.GRAY) +
                    if (player.player != null)
                        cmp(player.player!!.world.name, NamedTextColor.GOLD) + cmp(" ") +
                        cmp(player.player!!.location.x.roundToInt(), NamedTextColor.AQUA) + cmp(" ") +
                        cmp(player.player!!.location.y.roundToInt(), NamedTextColor.AQUA) + cmp(" ") +
                        cmp(player.player!!.location.z.roundToInt(), NamedTextColor.AQUA)
                    else
                        cmp("No Location Found", NamedTextColor.RED)
        val bed = cmp("Bed: ", NamedTextColor.GRAY) +
                if (player.player != null && player.player?.bedSpawnLocation != null)
                    cmp(player.player!!.bedSpawnLocation!!.world.name, NamedTextColor.GOLD) + cmp(" ") +
                            cmp(player.player!!.bedSpawnLocation!!.x.roundToInt(), NamedTextColor.AQUA) + cmp(" ") +
                            cmp(player.player!!.bedSpawnLocation!!.y.roundToInt(), NamedTextColor.AQUA) + cmp(" ") +
                            cmp(player.player!!.bedSpawnLocation!!.z.roundToInt(), NamedTextColor.AQUA)
                else
                    cmp("No Location Found", NamedTextColor.RED)

        return listOf(current, bed)
    }

    val coordsCommand = command("coordinates", "Displays Current Coordinates/Bed Coordinates of a Player", listOf("coords")) { player, args ->
        if (args.isEmpty()) {
            player.sendMessage(cmp("#coordinates <players>", NamedTextColor.RED))
            return@command
        }
        for (name in args) {
            val coords = getCoords(Bukkit.getOfflinePlayer(name))
            player.sendMessage(coords[0])
            player.sendMessage(coords[1])
        }
    }
}