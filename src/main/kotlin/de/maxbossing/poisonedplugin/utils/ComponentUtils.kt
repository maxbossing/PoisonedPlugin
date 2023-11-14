package de.maxbossing.poisonedplugin.utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

fun cmp(text: Any, color: TextColor = NamedTextColor.GRAY, bold: Boolean = false, italic: Boolean = false, underlined: Boolean = false, obfuscated: Boolean = false ): Component {
    return Component
        .text(text.toString())
        .color(color)
        .decorations(
            mapOf(
                TextDecoration.BOLD to TextDecoration.State.byBoolean(bold),
                TextDecoration.UNDERLINED to TextDecoration.State.byBoolean(underlined),
                TextDecoration.ITALIC to TextDecoration.State.byBoolean(italic),
                TextDecoration.OBFUSCATED to TextDecoration.State.byBoolean(obfuscated),
            )
        )
}

operator fun Component.plus(other: Component): Component = append(other)