package de.maxbossing.poisonedplugin.module.luckperms

import de.maxbossing.poisonedplugin.manager.command
import de.maxbossing.poisonedplugin.utils.cmp
import de.maxbossing.poisonedplugin.utils.plus
import net.kyori.adventure.text.format.NamedTextColor
import net.luckperms.api.LuckPerms
import net.luckperms.api.LuckPermsProvider
import net.luckperms.api.node.Node

object LuckPermsModule {

    val api = LuckPermsProvider.get()

    val luckPermsCommand = command("luckperms", "Manages LuckPerms-related modules", listOf("lp")) { player, args ->
        if (args.isEmpty()) {
            player.sendMessage(cmp("#luckperms <add <permissions> | remove <permission>>", NamedTextColor.RED))
            return@command
        }

        if (args.size > 1) {
            when (args[0]) {
                "add" -> {
                    api.userManager.modifyUser(player.uniqueId) {
                        for (perm in args.drop(1)) {
                            val result = it.data().add(Node.builder(perm).value(true).build())
                            if (result.wasSuccessful()) {
                                player.sendMessage(cmp(perm) + cmp(" added", NamedTextColor.GREEN))
                            } else {
                                player.sendMessage(cmp(perm) + cmp(" already added", NamedTextColor.RED))
                            }
                        }
                    }
                }
                "remove" -> {
                    api.userManager.modifyUser(player.uniqueId) {
                        for (perm in args.drop(1)) {
                            val result = it.data().remove(Node.builder(perm).value(true).build())
                            if (result.wasSuccessful()) {
                                player.sendMessage(cmp(perm) + cmp(" removed", NamedTextColor.GREEN))
                            } else {
                                player.sendMessage(cmp(perm) + cmp(" already removed", NamedTextColor.RED))
                            }
                        }
                    }
                }
            }
        }
    }
}