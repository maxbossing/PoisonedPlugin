package de.maxbossing.poisonedplugin.command

import de.maxbossing.poisonedplugin.manager.command
import de.maxbossing.poisonedplugin.utils.cmp
import net.kyori.adventure.text.Component
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta

object GameModeCommand {
    val gameModeCommand = command("gamemode", "Changes your Gamemode", listOf("gm")) {player, args ->
        if (args.isEmpty()) {
            player.sendMessage(cmp("#gamemode <creative | survival | spectator | adventure | 0 | 1 | 2 | 3>"))
            return@command
        }

        when (args[0].lowercase()) {
            "creative", "1" -> player.gameMode = GameMode.CREATIVE
            "survival", "0" -> player.gameMode = GameMode.SURVIVAL
            "adventure", "2" -> player.gameMode = GameMode.ADVENTURE
            "spectator", "3" -> player.gameMode = GameMode.SPECTATOR
        }
    }
}