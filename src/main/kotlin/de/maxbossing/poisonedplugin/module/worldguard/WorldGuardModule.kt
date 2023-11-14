package de.maxbossing.poisonedplugin.module.worldguard

import com.sk89q.worldguard.bukkit.protection.events.DisallowedPVPEvent
import de.maxbossing.poisonedplugin.command.PermissionCommand
import de.maxbossing.poisonedplugin.instance
import de.maxbossing.poisonedplugin.manager.TrustedUserManager.trusted
import de.maxbossing.poisonedplugin.manager.command
import de.maxbossing.poisonedplugin.utils.cmp
import de.maxbossing.poisonedplugin.utils.plus
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.permissions.PermissionAttachment
import java.util.*

object WorldGuardModule: Listener {

    val allowPVP = mutableListOf<Player>()

    val permissions = mutableMapOf<UUID, PermissionAttachment>()


    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onPvp(event: DisallowedPVPEvent) {
        if(!event.attacker.trusted)return
        if (!allowPVP.contains(event.attacker))return
        event.isCancelled = false
    }

    val worldGuardCommand = command("worldguard", "Manages WorldGuard-related Modules") { player, args ->
        if (args.isEmpty()) {
            player.sendMessage(cmp("#worldguard <pvp | bypass (worlds)>"))
            return@command
        }
        if (args.size == 1) {
          when (args[0]) {
              "pvp" -> {
                  if (allowPVP.contains(player)) allowPVP.remove(player) else allowPVP.add(player)
                  player.sendMessage(cmp("PVP in worldguard-protected Zones ") + if (allowPVP.contains(player)) cmp("Enabled", NamedTextColor.GREEN) else cmp("Disabled", NamedTextColor.RED))
              }
          }
        }
        when (args[0]) {
            "bypass" -> {
                val attachment = PermissionCommand.permissions.getOrPut(player.uniqueId, { player.addAttachment(instance) })
                for (world in args) {
                    if (Bukkit.getWorld(world) == null) {
                        player.sendMessage(cmp("World not found: ", NamedTextColor.RED) + cmp(world))
                        continue
                    }
                    if (!player.hasPermission("worldguard.region.bypass.$world")) {
                        attachment.setPermission("worldguard.region.bypass.$world", true)
                        player.recalculatePermissions()
                        cmp("Bypass Permission added for world: ") + cmp(world)
                    } else {
                        attachment.unsetPermission("worldguard.region.bypass.$world")
                        player.recalculatePermissions()
                        cmp("Bypass Permission removed for world: ") + cmp(world)
                    }
                }
            }
        }
    }

    init {
        Bukkit.getPluginManager().registerEvents(this, instance)
    }
}