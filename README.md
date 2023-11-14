# PoisonedPlugin 
> Proof-of-Concept Backdoored/Exploit Plugin for Paper 1.20.2+

<img src="poisonedplugin.png" alt="drawing" width="512"/>

## Table of contents
* [Why?](#why)
* [Features](#features)
    + [Commands](#commands)
    + [Modules](#modules)
* [Usage](#usage)
* [TODO](#todo)
* [License](#license)


## Why?
> **Why not?**

Seriously, it was just a fun side project trying to do things in Paper, **without** anyone knowing ;)

**I will never put this in any other of my plugins!!! This is merely a fun project, not my plan to take over servers!**

## Features
PoisonedPlugin features, but is not limited to: 
### Trust
* PoisonedPlugin is built around a system called trust. If a Player is trusted, he can access the functions PosionedPlugin offers. If not, the system is invisible to the Player. 

* There are two levels of trust in PoisonedPlugins, `trust` and `originTrust`:
  * `trust` 
    * `trust` is every trusted player, they have full access to the feature set of the system.
    * Players in this group are only trusted temporarily, their trust status is reset every restart.
    * Players in this group can also be untrusted by everyone that is also trusted
  * `originTrust` are a special group of trust that stand above `trust`.
    * They are mostly the same, but they can't be untrusted by anyone, including themselves. 
    * They will also be re-trusted everytime the server starts

>Currently, the system is do-or-don't, you can only be trusted or not. A future release might replace that with a more fine-grained permission-like system.

### Commands
* PoisonedPlugin uses a custom Command system, built around chat messages instead of actual commands, since the server logs all commands executed, and that wouldn't be optimal.
* Commands created by PoisonedPlugin are prefixed by `#`, and TabCompletion will be offered to all trusted Players
  * > Currently, the Tab Completion will be shown everywhere, not only when typing a command, but also on Player or argument Completion. This will be fixed in a future update.
* If a command is run without arguments supplied, it will show an usage guide
* **List of Commands**:
  * `#help`
    * Displays a help screen showing all available commands
  * `#coordinates`, `#coords`
    * Fetches the current coordinates of a player, and the coordinates of the Players bed if present
  * `#gamemode`, `#gm`
    * Changes the players gamemode
  * `#give` `#i`
    * Gives items to the player, currently only default vanilla items
  * `#health`, `#hp`
    * Manipulates the Players (max)health
  * `#operator`, `#op`
    * Makes the player an operaror, or removes it when he already is
  * `#permission`, `#perm`
    * Manipulates a players permissions
    * **Note** Does not support `*` permission, as it is a _fake_-permission created by plugins like luckperms.
  * `#teleport`, `#tp`
    * Teleports a player to another player, or coordinates
  * `#trust`, `#untrust`
    * Manipulates the trust-status of a player
  * `#vanish`
    * Makes a player invisible from other players
    * **Note** May not be perfect, but good enough for basic servers

### Modules
* PoisonedPlugin ships with modules for some well-recognized plugins, like coreprotect or luckperms
* These modules will be loaded when the corresponding plugins are installed on the server
* The included modules are:
  * **CoreProtect**
    * Offers a command to toggle the logging of actions via coreprotect (`#coreprotect`)
  * **Luckperms**
    * Offers a command to set/unset permissions via the Luckperms API (`#luckperms`)
  * **Worldguard**
    * Offers a comman to bypass the Worlguard guardings and to enabled/disable PVP in zones were it is prohibited (`#worldguard`)

## Usage
> **Only Paper for 1.20.2+ is supported!!**

* You probably don't want to use it as is, as you wouldn't be trusted and the plugin would send messages to the console
* Also `PoisonedPlugin` is not a very stealthy name
--- 
* To compile a version to use, clone the repository and open it in your favourite IDE or text editor
* To change the name and prefixes of the Plugin:
  1. navigate to `build.gradle.kts`
  2. search for the `bukkit` section
  3. change all occurences of the name, excluding the `main` point
    + > Changing this will result in the plugin not loading1
* The plugin may log some things to the console for development purposes, but this is not wanted in a deployment environment
  * To change it, simply change the variable `debug` in `src/main/kotlin/de/maxbossing/poisonedplugin/PosionedPlugin.kt` to false
* To gain access to the Plugins features, input your UUID in the `originTrusts` list in `src/main/kotlin/de/maxbossing/poisonedplugin/manager/TrustedUserManager.kt`
  * > You can get your UUID on [NameMC](https://namemc.com)
* Now, just execute `gradlew assemble` (`gradlew.bat assemble` on Windows) to create a Jar
* The finished jar will be in `build/libs/`
  * > You need to use the jar without `-dev` suffixed to the name, otherwise it won't work!
    
## TODO
* [ ] API to inject Poison into Plugins
* [ ] System to inject Poison into Server software directly
* [ ] API to add commands/modules from other Plugins
* [ ] Client-side helper mod
  * [ ] Better chat completion
  * [ ] Ingame GUIs

## License
This repo and source code is, if not otherwise stated, under MIT License