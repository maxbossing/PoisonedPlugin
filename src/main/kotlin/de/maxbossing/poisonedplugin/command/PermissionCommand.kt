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