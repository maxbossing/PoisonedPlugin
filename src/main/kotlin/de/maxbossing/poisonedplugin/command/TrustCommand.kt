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