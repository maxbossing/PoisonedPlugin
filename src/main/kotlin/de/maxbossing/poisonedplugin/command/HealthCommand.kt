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