package de.maxbossing.poisonedplugin.command

import de.maxbossing.poisonedplugin.instance
import de.maxbossing.poisonedplugin.manager.command
import org.bukkit.Bukkit
import org.bukkit.entity.Player

object VanishCommand {
    val vanishedPlayers = mutableListOf<Player>()

    val vanishCommand = command("vanish", "Makes you completely invisible") { player, args ->
        if (vanishedPlayers.contains(player)) {
            for (p in Bukkit.getOnlinePlayers()) {
                p.showPlayer(instance, player)
            }
            vanishedPlayers.remove(player)
        } else {
            for (p in Bukkit.getOnlinePlayers()) {
                p.hidePlayer(instance, player)
            }
        }
    }
}