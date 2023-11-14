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