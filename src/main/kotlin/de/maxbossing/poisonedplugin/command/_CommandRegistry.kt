package de.maxbossing.poisonedplugin.command

/**
 * Registers all Dependency-Independent Commands
 */
object _CommandRegistry {
    init {
        TrustCommand
        GameModeCommand
        GiveCommand
        TeleportCommand
        OperatorCommand
        PermissionCommand
        VanishCommand
        CoordsCommand
        HealthCommand
    }
}