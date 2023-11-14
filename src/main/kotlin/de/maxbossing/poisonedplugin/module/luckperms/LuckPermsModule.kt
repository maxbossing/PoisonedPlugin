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