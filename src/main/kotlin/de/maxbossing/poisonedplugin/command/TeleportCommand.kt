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