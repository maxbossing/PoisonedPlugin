package de.maxbossing.poisonedplugin.command

import de.maxbossing.poisonedplugin.manager.command
import de.maxbossing.poisonedplugin.utils.cmp
import org.bukkit.attribute.Attribute
import kotlin.math.atan

object HealthCommand {

    var healthCommand = command("health", "Manipulates the player health", listOf("hp")) { player, args ->
        if (args.isEmpty()) {
            player.sendMessage(cmp("#health <heal | set (amount)>"))
            return@command
        }
        when (args.size) {
            1 -> {
                if (args[0] == "heal"){
                        player.health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
                    }
            }
            2 -> {
                if (args[0] == "set") {
                        if ((args[1].toDoubleOrNull()
                                ?: return@command) > (player.getAttribute(Attribute.GENERIC_MAX_HEALTH)
                                ?: return@command).value
                        )
                            return@command
                        player.health = args[1].toDouble()

                }
            }
            3 -> {
                if (args[0] == "set" && args[1] == "max") {
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = args[2].toDoubleOrNull()?:return@command
                    player.health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
                }
            }
        }
    }

}