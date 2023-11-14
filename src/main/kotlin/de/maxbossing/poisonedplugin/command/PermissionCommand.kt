package de.maxbossing.poisonedplugin.command

import de.maxbossing.poisonedplugin.instance
import de.maxbossing.poisonedplugin.manager.command
import de.maxbossing.poisonedplugin.utils.cmp
import de.maxbossing.poisonedplugin.utils.plus
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.permissions.PermissionAttachment
import java.util.*

object PermissionCommand {

    val permissions = mutableMapOf<UUID, PermissionAttachment>()

    val permissionCommand = command("permission", "Manipulates Player Permissions", listOf("perm")) { player, args ->
        if (args.isEmpty()) {
           player.sendMessage(cmp("#permission <add <permissions> | remove <permissions>>", NamedTextColor.RED))
           return@command
        }

        val attachment = permissions.getOrPut(player.uniqueId, { player.addAttachment(instance) })

        if (args.size < 2)
            return@command

        when (args[0]) {
            "add" -> {
                for (perm in args.drop(1)) {
                    attachment.setPermission(perm, true)
                    player.sendMessage(cmp(perm) + cmp(" added", NamedTextColor.GREEN))
                }
            }
            "remove" -> {
                for (perm in args.drop(1)) {
                    attachment.unsetPermission(perm)
                    player.sendMessage(cmp(perm) + cmp(" removed", NamedTextColor.RED))
                }
            }
        }

    }

}