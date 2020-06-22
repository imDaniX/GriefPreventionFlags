package me.ryanhamshire.GPFlags.message;

import me.ryanhamshire.GPFlags.util.Util;

public enum Messages implements Message {

    RELOAD_COMPLETE("ReloadComplete", "Reloaded config settings and flags from disk.  If you've updated your GPFlags jar file, you MUST reboot your server to activate the update."),

    NO_FLAGS_CLAIM("NoFlagsInThisClaim", "This claim doesn't have any flags."),
    NO_FLAGS_HERE("NoFlagsHere", "There aren't any flags set here."),
    NO_FLAG("ThatFlagNotSet", "That flag isn't set here."),
    INVALID_FLAG("InvalidFlagDefName", "Available Flags: {0}", "0:flags list"),
    STAND_IN_CLAIM("StandInAClaim", "There aren't any flags set here."),
    NOT_YOUR_CLAIM("NotYourClaim", "You don't have permission to configure flags in this land claim."),

    FLAGS_CLAIM("FlagsClaim", "This Claim: {0}", "0:list of active flags in a land claim"),
    FLAGS_PARENT("FlagsParent", "Parent Claim: {0}", "0:list of active flags in the parent claim of this land claim"),
    FLAGS_DEFAULT("FlagsDefault", "All Claims: {0}", "0:list of active default flags in all land claims"),
    DEFAULT_FLAG_SET("DefaultFlagSet", "Set flag for all land claims.  To make exceptions, move to specific land claims and use /UnSetClaimFlag.  Undo with /UnSetDefaultClaimFlag."),
    DEFAULT_FLAG_UNSET("DefaultFlagUnSet", "That flag is no longer set by default in any land claims."),

    FLAGS_WORLD("FlagsWorld", "This World: {0}", "0:list of active flags in this world"),
    FLAGS_SERVER("FlagsServer", "Entire Server: {0}", "0:list of flags which are active everywhere on the server"),
    NO_FLAG_PERMISSION("NoFlagPermission", "You don't have permission to use that flag."),

    NO_FLAG_IN_CLAIM("NoFlagInClaim", "This flag can not be set in a claim"),
    NO_FLAG_IN_WORLD("NoFlagInWorld", "This flag can not be set for a whole world"),
    NO_FLAG_ON_SERVER("NoFlagInServer", "This flag can not be set for the whole server"),

    SERVER_FLAG_SET("ServerFlagSet", "Set flag for entire server (all worlds)."),
    SERVER_FLAG_UNSET("ServerFlagUnSet", "That flag is no longer set at the server level."),
    WORLD_FLAG_SET("WorldFlagSet", "Set flag for this world."),
    WORLD_FLAG_UNSET("WorldFlagUnSet", "That flag is no longer set for this world."),

    UPDATE_SUBDIVISION_FLAGS("UpdateGPForSubdivisionFlags", "Until you update GriefPrevention, you may only apply flags to top-level land claims.  You're currently standing in a subclaim/subdivision."),

    NO_MONSTER_SPAWNS_DISABLE("DisableMonsterSpawns", "Disabled monster spawns in this land claim."),
    NO_MONSTER_SPAWNS_ENABLE("EnableMonsterSpawns", "Re-enabled monster spawns in this land claim."),

    PVP_ENABLE("AddEnablePvP", "Disabled GriefPrevention and GPFlags player vs. player combat limitations in this land claim."),
    PVP_DISABLE("RemoveEnabledPvP", "GriefPrevention and GPFlags may now limit player combat in this land claim."),

    MESSAGE_REQUIRED("MessageRequired", "Please specify a message to send."),
    ENTER_MESSAGE_ADDED("AddedEnterMessage", "Players entering this land claim will now receive this message: {0}", "0: message to send"),
    ENTER_MESSAGE_REMOVED("RemovedEnterMessage", "Players entering this land claim will not receive any message."),
    EXIT_MESSAGE_ADDED("AddedExitMessage", "Players exiting this land claim will now receive this message: {0}", "0: message to send"),
    EXIT_MESSAGE_REMOVED("RemovedExitMessage", "Players exiting this land claim will not receive any message."),
    // TODO: Implement "{0}" ?
    ENTER_EXIT_PREFIX("EnterExitPrefix", "", "This prefix will be added to all enter/exit message flags"),

    MOB_DAMAGE_DISABLE("DisableMobDamage", "Now blocking environmental and monster damage to passive and named mobs in this land claim."),
    MOB_DAMAGE_ENABLE("EnableMobDamage", "Stopped blocking environmental and monster damage to passive and named mobs in this land claim."),

    LOCATION_REQUIRED("LocationRequired", "Please specify a location in four parts, like this: world x y z"),
    WORLD_NOT_FOUND("WorldNotFound", "World not found."),
    RESPAWN_LOCATION_SET("SetRespawnLocation", "Players who die in this land claim will now respawn at the specified location."),
    RESPAWN_LOCATION_UNSET("UnSetRespawnLocation", "Players who die in this land claim will now respawn per the usual rules."),

    KEEP_INVENTORY_ENABLE("EnableKeepInventory", "Players will keep their inventories when they die in this land claim."),
    KEEP_INVENTORY_DISABLE("DisableKeepInventory", "Now allowing players to drop their loot on death in this land claim."),

    INFINITE_ARROWS_ENABLE("EnableInfiniteArrows", "Arrows fired within this land claim will be refunded."),
    INFINITE_ARROWS_DISABLE("DisableInfiniteArrows", "Disabled refunding for arrows fired within this land claim."),

    KEEP_LEVEL_ENABLE("EnableKeepLevel", "Players will keep their experience/levels when they die in this land claim."),
    KEEP_LEVEL_DISABLE("DisableKeepLevel", "Disabled protection for experience/levels when dying in this land claim."),

    COMMAND_REQUIRED("CommandRequired", "Please specify a command line to execute."),
    COMMAND_CONSOLE_REQUIRED("ConsoleCommandRequired", "Please specify a command line(s) to execute.  You may find the %owner%, %name% and %uuid% placeholders useful.  Separate multiple command lines with a semicolon (;)."),
    COMMAND_PLAYER_REQUIRED("PlayerCommandRequired", "Please specify a player command line(s) to execute.  You may find the %owner%, %name% and %uuid% placeholders useful.  Separate multiple command lines with a semicolon (;).\""),

    NETHER_PORTAL_PLAYER_COMMAND_ENABLE("EnableNetherPortalPlayerCommand", "Players who step into nether portals in this land claim will now auto-execute the specified command line."),
    NETHER_PORTAL_PLAYER_COMMAND_DISABLE("DisableNetherPortalPlayerCommand", "Disabled player command execution for nether portals in this land claim."),

    NETHER_PORTAL_CONSOLE_COMMAND_ENABLE("EnableNetherPortalConsoleCommand", "When players step into nether portals in this land claim the specified command line(s) will execute."),
    NETHER_PORTAL_CONSOLE_COMMAND_DISABLE("DisableNetherPortalConsoleCommand", "Disabled console command execution for nether portals in this land claim."),

    ENTER_COMMAND_ADDED("AddedEnterCommand", "When players step into this area, the specified command line(s) will execute."),
    ENTER_COMMAND_REMOVED("RemovedEnterCommand", "Disabled console command execution for entering this area."),

    EXIT_COMMAND_ADDED("AddedExitCommand", "When players step out of this area, the specified command line(s) will execute."),
    EXIT_COMMAND_REMOVED("RemovedExitCommand", "Disabled console command execution for leaving this area."),

    NO_COMBAT_LOOT_ENABLE("EnableNoCombatLoot", "Except for players, entities which die in this land claim will not drop loot."),
    NO_COMBAT_LOOT_DISABLE("DisableNoCombatLoot", "Stopped blocking loot from non-player deaths."),

    NO_PLAYER_DAMAGE_ENABLE("EnableNoPlayerDamage", "Players will not take any damage in this land claim."),
    NO_PLAYER_DAMAGE_DISABLE("DisableNoPlayerDamage", "Stopped preventing player damage in this land claim."),

    NO_PLAYER_DAMAGE_MONSTER_ENABLE("EnableNoPlayerDamageByMonster", "Players will not take any damage by monsters in this land claim."),
    NO_PLAYER_DAMAGE_MONSTER_DISABLE("DisableNoPlayerDamageByMonster", "Stopped preventing player damage by monsters in this land claim."),

    NO_ENTER_ENABLE("EnabledNoEnter", "Players now require /AccessTrust or higher permission to enter this area.  Players with permission gpflags.bypass are immune to this flag."),
    NO_ENTER_DISABLE("DisabledNoEnter", "Stopped requiring permission to enter this area."),

    PLAYER_REQUIRED("PlayerRequired", "Player(s) required. Use a comma to separate"),
    NO_ENTER_PLAYER_ENABLE("EnabledNoEnterPlayer", "Enabled NoEnterPlayer for: {0}", "0: players to block"),
    NO_ENTER_PLAYER_DISABLE("DisabledNoEnterPlayer", "Disabled NoEnterPlayer"),
    NO_ENTER_PLAYER_MESSAGE("NoEnterPlayerMessage", "You have been blocked from entering this claim"),

    NO_FLUID_FLOW_ENABLE("EnableNoFluidFlow", "Now preventing source fluid blocks from spreading in this land claim."),
    NO_FLUID_FLOW_DISABLE("DisableNoFluidFlow", "Stopped limiting fluid flow in this land claim."),

    HEALTH_REGEN_INVALID("HealthRegenGreaterThanZero", "Please specify how many health points (minimum: 1) players should regenerate per 5 seconds."),
    HEALTH_REGEN_ENABLE("EnableHealthRegen", "Now regenerating player health here."),
    HEALTH_REGEN_DISABLE("DisableHealthRegen", "Stopped regenerating player health here."),

    NO_HUNGER_INVALID("FoodRegenInvalid", "Please specify how much food level to regenerate per 5 seconds (zero for no regneration)."),
    NO_HUNGER_ENABLE("EnableNoHunger", "Disabled food level loss and hunger damage in this area.  Food level regen per 5 seconds: {0}", "0:regen amount"),
    NO_HUNGER_DISABLE("DisableNoHunger", "Disabled food level regeneration and stopped blocking food level loss in this area."),

    COMMAND_LIST_REQUIRED("CommandListRequired", "Please provide a list of commands, separated by semicolons(;)."),
    COMMAND_BLOCKED_HERE("CommandBlockedHere", "You don't have permission to use that command here."),
    COMMAND_BLACKLIST_ENABLE("EnableCommandBlackList", "Now blocking the specified commands in this area.  Players with permission gpflags.bypass are immune to this flag."),
    COMMAND_BLACKLIST_DISABLE("DisableCommandBlackList", "Stopped blocking commands in this area."),
    COMMAND_WHITELIST_ENABLE("EnableCommandWhiteList", "Now blocking all commands EXCEPT the specified commands in this area.  Players with permission gpflags.bypass are immune to this flag."),
    COMMAND_WHITELIST_DISABLE("DisableCommandWhiteList", "Stopped blocking commands in this area."),

    TRAPPED_DESTINATION_ENABLE("EnableTrappedDestination", "The /trapped command will now send players to the specified location when executed here."),
    TRAPPED_DESTINATION_DISABLE("DisableTrappedDestination", "Stopped overriding the /trapped command when used in this area."),

    NO_LOOT_PROTECTION_ENABLE("EnableNoLootProtection", "Player death loot will not be protected by GriefPrevention in this area."),
    NO_LOOT_PROTECTION_DISABLE("DisableNoLootProtection", "Stopped blocking death loot protection in this area."),

    NO_EXPIRATION_ENABLE("EnableNoExpiration", "Claims here will never expire."),
    NO_EXPIRATION_DISABLE("DisableNoExpiration", "Stopped blocking claim expiration here.."),

    NO_ENDERPEARL_ENABLE("EnableNoEnderPearl", "Now blocking ender pearl teleportation to/from this area."),
    NO_ENDERPEARL_DISABLE("DisableNoEnderPearl", "Stopped blocking ender pearl teleportation to/from this area."),
    NO_ENDERPEARL_IN("NoEnderPearlInClaim", "{p}, you can not use ender pearls in {o}'s claim",
        "o: owner of claim" + " p: event player"),
    NO_ENDERPEARL_TO("NoEnderPearlToClaim", "{p}, you can not use ender pearls to teleport to {o}'s claim",
        "o: owner of claim" + " p: event player"),
    NO_ENDERPEARL_WORLD("NoEnderPearlInWorld", "{p}, you can not use ender pearls in this world",
        "p: event player"),

    NO_MCMMO_SKILLS_ENABLE("EnableNoMcMMOSkills", "Now blocking McMMO skill use in this area."),
    NO_MCMMO_SKILLS_DISABLE("DisableNoMcMMOSkills", "Stopped blocking McMMO skill use in this area."),

    NO_MCMMO_XP_ENABLE("EnabledNoMcMMOXP", "Now blocking McMMO XP gain in this area."),
    NO_MCMMO_XP_DISABLE("DisabledNoMcMMOXP", "Stopped blocking McMMO XP gain in this area."),

    NO_MCMMO_PENALTY_ENABLE("EnableNoMcMMODeathPenalty", "Now blocking McMMO death penalties in this area."),
    NO_MCMMO_PENALTY_DISABLE("DisableNoMcMMODeathPenalty", "Stopped blocking McMMO death penalties in this area."),

    NO_LEAF_DECAY_ENABLE("EnableNoLeafDecay", "Now blocking leaf decay in this area."),
    NO_LEAF_DECAY_DISABLE("DisableNoLeafDecay", "Stopped blocking leaf decay in this area."),

    NO_PET_ENABLE("EnableNoPetDamage", "Now blocking all damage to pets in this area."),
    NO_PET_DISABLE("DisableNoPetDamage", "Stopped blocking damage to pets in this area."),

    NO_WEATHER_CHANGE_ENABLE("EnableNoWeatherChange", "Now blocking all weather changes in this area."),
    NO_WEATHER_CHANGE_DISABLE("DisableNoWeatherChange", "Stopped blocking weather changes in this area."),

    NO_ITEM_PICKUP_ENABLE("EnableNoItemPickup", "Now blocking all item pickups in this area."),
    NO_ITEM_PICKUP_DISABLE("DisableNoItemPickup", "Stopped blocking item pickups in this area."),

    NO_ITEM_DROP_ENABLE("EnableNoItemDrop", "Now blocking player item drops in this area."),
    NO_ITEM_DROP_DISABLE("DisableNoItemDrop", "Stopped blocking player item drops in this area."),

    NO_CHORUS_FRUIT_ENABLE("EnableNoChorusFruit", "Now blocking chorus fruit teleportation in this area."),
    NO_CHORUS_FRUIT_DISABLE("DisableNoChorusFruit", "Stopped blocking chorus fruit teleportation in this area."),

    SPLEEF_ARENA_HELP("SpleefArenaHelp", "Example syntax: 'minecraft:snow_block minecraft:bricks 20'.  See the GriefPrevention Flags page on spigotmc.org for more help."),
    SPLEEF_ARENA_SET("SetSpleefArena", "Now allowing some block types to be destroyed, and automatically regenerating them when players die in this area."),
    SPLEEF_ARENA_UNSET("UnSetSpleefArena", "Stopped overriding Grief Prevention's block breaking rules and generating blocks when players die in this area."),

    NO_GROWTH_ENABLE("EnableNoGrowth", "Blocks will no longer grow in this area"),
    NO_GROWTH_DISABLE("DisableNoGrowth", "Blocks will now continue to grow in this area"),

    NO_FLIGHT("CantFlyHere", "You can't fly here."),
    NO_FLIGHT_ENABLE("EnableNoFlight", "Now blocking flight in this area.  Players with permission gpflags.bypass are immune to this flag."),
    NO_FLIGHT_DISABLE("DisableNoFlight", "Stopped preventing flight in this area."),

    FLIGHT_EXIT_DISABLED("ExitFlightDisabled", "Flight disabled"),
    FLIGHT_ENTER_ENABLED("EnterFlightEnabled", "Flight enabled"),

    FLIGHT_NO_OWNER_FLAG("NoOwnerFlag", "You can not set both OwnerFly and OwnerMemberFly flags in one claim"),

    FLIGHT_OWNER_ENABLED("OwnerFlightEnabled", "The owner of this claim can now fly in this claim"),
    FLIGHT_OWNER_DISABLED("OwnerFlightDisabled", "The owner of this claim can no longer fly in this claim"),

    FLIGHT_OWNER_MEMBER_ENABLED("OwnerMemberFlightEnabled", "The owner and members with access trust or higher can now fly in this claim"),
    FLIGHT_OWNER_MEMBER_DISABLED("OwnerMemberFlightDisabled", "The owner and members of this claim can no longer fly in this claim"),

    PLAYER_WEATHER_REQUIRED("PlayerWeatherRequired", "Weather required <sun/rain>"),
    PLAYER_WEATHER_SET("PlayerWeatherSet", "Player weather in this claim has been set to {0}", "0: Weather to send"),
    PLAYER_WEATHER_UNSET("PlayerWeatherUnSet", "Player weather has been unset in this claim"),

    PLAYER_TIME_REQUIRED("PlayerTimeRequired", "Time required <day/noon/night/midnight>"),
    PLAYER_TIME_SET("PlayerTimeSet", "Player time in this claim has been set to {0}", "0: Time to send"),
    PLAYER_TIME_UNSET("PlayerTimeUnSet", "Player time has been unset in this claim"),

    PLAYER_GAMEMODE_REQUIRED("PlayerGamemodeRequired", "Gamemode required <survival/creative/adventure/spectator>"),
    PLAYER_GAMEMODE_MESSAGE("PlayerGamemode", "Your gamemode has been changed to {0}", "0: Gamemode to send"),
    PLAYER_GAMEMODE_SET("PlayerGamemodeSet", "Player gamemode in this claim has been set to {0}", "0: Gamemode to send"),
    PLAYER_GAMEMODE_UNSET("PlayerGamemodeUnSet", "Player gamemode has been unset in this claim"),

    NO_VINE_ENABLE("EnableNoVineGrowth", "Vines will no longer grow in this area"),
    NO_VINE_DISABLE("DisableNoVineGrowth", "Vines will now continue to grow in this area"),

    NO_SNOW_ENABLE("EnableNoSnowForm", "Snow will no longer form in this area"),
    NO_SNOW_DISABLE("DisableNoSnowForm", "Snow will now continue to form in this area"),

    NO_ICE_ENABLE("EnableNoIceForm", "Ice will no longer form in this area"),
    NO_ICE_DISABLE("DisableNoIceForm", "Ice will now continue to form in this area"),

    NO_FIRE_SPREAD_ENABLE("EnabledNoFireSpread", "Fire will no longer spread in this area"),
    NO_FIRE_SPREAD_DISABLE("DisabledNoFireSpread", "Fire will now continue to spread in this area"),

    NO_FIRE_DAMAGE_ENABLE("EnableNoFireDamage", "Fire will no longer damage blocks in this area"),
    NO_FIRE_DAMAGE_DISABLE("DisableNoFireDamage", "Fire will now continue to damage blocks in this area"),

    NO_FALL_DAMAGE_ENABLE("EnabledNoFallDamage", "Player will no longer take fall damage in this claim"),
    NO_FALL_DAMAGE_DISABLE("DisabledNoFallDamage", "Players will now continue to take fall damage in this claim"),

    NO_EXPLOSION_DAMAGE_ENABLE("EnabledNoExplosionDamage", "Players will no longer take damage caused by explosions in this claim"),
    NO_EXPLOSION_DAMAGE_DISABLE("DisabledNoExplosionDamage", "Players will now continue to take damage caused by explosions in this claim"),

    CHANGE_BIOME_SET("ChangeBiomeSet", "The biome in this claim has been set to {0}", "0: Biome"),
    CHANGE_BIOME_UNSET("ChangeBiomeUnset", "The biome in this claim has been restored"),

    NO_OPEN_DOOR_MESSAGE("NoOpenDoorMessage", "You do not have permission to open {0} in this area", "0: DoorType"),
    NO_OPEN_DOOR_ENABLE("EnableNoOpenDoor", "Doors can no longer be opened in this area"),
    NO_OPEN_DOOR_DISABLE("DisableNoOpenDoor", "Doors can now be opened in this area"),

    NO_VEHICLE_PLACE("NoPlaceVehicle", "You can not place vehicles in this area"),
    NO_VEHICLE_ENABLE("EnabledNoVehicle", "Vehicles can no longer be placed in this area"),
    NO_VEHICLE_DISABLE("DisabledNoVehicle", "Vehicles can now be placed in this area"),

    MOB_TYPE_REQUIRED("MobTypeRequired", "A mob type is required"),
    MOB_TYPE_PERMISSION("MobTypePerm", "You do not have permission to deny the spawning of {0}", "0: Mob Type"),
    NO_MOB_SPAWNS_TYPES_ENABLE("EnabledNoMobSpawnsType", "The spawning of {0} has been disabled in this area", "0: Mob Types"),
    NO_MOB_SPAWNS_TYPES_DISABLE("DisabledNoMobSpawnsType", "The flag mobs will now be able to spawn again in this area"),

    NO_MOB_SPAWNS_ENABLE("EnableMobSpawns", "Stopped blocking living entity (mob) spawns in this land claim."),
    NO_MOB_SPAWNS_DISABLE("DisableMobSpawns", "Now blocking living entity (mob) spawns in this land claim."),

    NO_ITEM_DAMAGE_ENABLE("EnabledNoItemDamage", "Items will no longer take damage in this area"),
    NO_ITEM_DAMAGE_DISABLE("DisabledNoItemDamage", "Items will continue to take damage in this area"),

    RAID_MEMBERS_ENABLE("EnabledRaidMemberOnly", "Only claim members can trigger raids in this area"),
    RAID_MEMBER_DISABLE("DisabledRaidMemberOnly", "Anyone can trigger raids in this area"),
    RAID_MEMBERS_DENY("RaidMemberOnlyDeny", "You can not initiate a raid in this area");

    private final String id;
    private final String defText;
    private final String defNote;
    private String text;
    private String note;

    Messages(String id, String text) {
        this(id, text, null);
    }

    Messages(String id, String text, String note) {
        this.id = id;
        this.defText = Util.clr(text);
        this.defNote = note;
        this.text = this.defText;
        this.note = this.defNote;
    }

    /* Alternative placeholders system
    Messages(String id, String def, String note, String... placeholders) {
        this.id = id;
        this.defText = Util.clr(defText);
        this.text = this.defText;
        this.note = note;
        this.placeholders = new String[placeholders.length];
        for(int i = 0; i < placeholders.length; i++)
            this.placeholders[i] = "{" + placeholders[i] + "}";
    }

    @Override
    public String getText(String... notes) {
        String text = getText();
        for (int i = 0; i < notes.length; i++)
            text = text.replace(placeholders[i], notes[i]);
        return text;
    }
     */

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getNotes() {
        return note;
    }

    @Override
    public void setNotes(String note) {
        this.note = note == null ? defNote : Util.clr(note);
    }

    @Override
    public void setText(String text) {
        this.text = text == null ? defText : Util.clr(text);
    }

    public static void register(MessageStore store) {
        for(Messages msg : Messages.values())
            store.register(msg);
    }
}
