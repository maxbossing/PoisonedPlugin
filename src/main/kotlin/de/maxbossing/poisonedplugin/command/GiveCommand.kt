package de.maxbossing.poisonedplugin.command

import de.maxbossing.poisonedplugin.manager.command
import de.maxbossing.poisonedplugin.utils.cmp
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object GiveCommand {
    val giveCommand = command("give", "Gives you Items", listOf("i")) { player, args ->
        if (args.isEmpty()) {
            player.sendMessage(cmp("#give <Material> <Count>", NamedTextColor.RED))
            return@command
        }
        if (args.size == 1) {
            player.inventory.addItem(ItemStack(Material.getMaterial(args[0])?: return@command))
        } else if (args.size == 2) {
            repeat(args[1].toIntOrNull()?: return@command) {
                player.inventory.addItem(ItemStack(Material.getMaterial(args[0])?: return@command))
            }
        }
    }
}