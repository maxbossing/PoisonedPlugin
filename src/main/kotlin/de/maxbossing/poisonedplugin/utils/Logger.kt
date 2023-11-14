package de.maxbossing.poisonedplugin.utils

import de.maxbossing.poisonedplugin.PoisonedPlugin

fun log(vararg msg: String) {
    if (!PoisonedPlugin.debug)
        return
    msg.forEach {
        println("[PoisonedPlugin] [DEBUG] $it")
    }
}