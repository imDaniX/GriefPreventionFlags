package me.ryanhamshire.GPFlags;

import com.google.common.io.Files;
import me.ryanhamshire.GPFlags.flags.FlagDef_AllowPvP;
import me.ryanhamshire.GPFlags.flags.FlagDef_ChangeBiome;
import me.ryanhamshire.GPFlags.flags.FlagDef_CommandBlackList;
import me.ryanhamshire.GPFlags.flags.FlagDef_CommandWhiteList;
import me.ryanhamshire.GPFlags.flags.FlagDef_EnterCommand;
import me.ryanhamshire.GPFlags.flags.FlagDef_EnterCommand_Members;
import me.ryanhamshire.GPFlags.flags.FlagDef_EnterCommand_Owner;
import me.ryanhamshire.GPFlags.flags.FlagDef_EnterMessage;
import me.ryanhamshire.GPFlags.flags.FlagDef_EnterPlayerCommand;
import me.ryanhamshire.GPFlags.flags.FlagDef_ExitCommand;
import me.ryanhamshire.GPFlags.flags.FlagDef_ExitCommand_Members;
import me.ryanhamshire.GPFlags.flags.FlagDef_ExitCommand_Owner;
import me.ryanhamshire.GPFlags.flags.FlagDef_ExitMessage;
import me.ryanhamshire.GPFlags.flags.FlagDef_ExitPlayerCommand;
import me.ryanhamshire.GPFlags.flags.FlagDef_HealthRegen;
import me.ryanhamshire.GPFlags.flags.FlagDef_InfiniteArrows;
import me.ryanhamshire.GPFlags.flags.FlagDef_KeepInventory;
import me.ryanhamshire.GPFlags.flags.FlagDef_KeepLevel;
import me.ryanhamshire.GPFlags.flags.FlagDef_NetherPortalConsoleCommand;
import me.ryanhamshire.GPFlags.flags.FlagDef_NetherPortalPlayerCommand;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoChorusFruit;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoCombatLoot;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoEnderPearl;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoEnter;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoEnterPlayer;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoExpiration;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoExplosionDamage;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoFallDamage;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoFireDamage;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoFireSpread;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoFlight;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoFluidFlow;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoGrowth;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoHunger;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoIceForm;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoItemDamage;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoItemDrop;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoItemPickup;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoLeafDecay;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoLootProtection;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoMcMMODeathPenalty;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoMcMMOSkills;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoMcMMOXP;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoMobDamage;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoMobSpawns;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoMobSpawnsType;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoMonsterSpawns;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoOpenDoors;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoPetDamage;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoPlayerDamage;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoPlayerDamageByMonster;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoSnowForm;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoVehicle;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoVineGrowth;
import me.ryanhamshire.GPFlags.flags.FlagDef_NoWeatherChange;
import me.ryanhamshire.GPFlags.flags.FlagDef_OwnerFly;
import me.ryanhamshire.GPFlags.flags.FlagDef_OwnerMemberFly;
import me.ryanhamshire.GPFlags.flags.FlagDef_PlayerGamemode;
import me.ryanhamshire.GPFlags.flags.FlagDef_PlayerTime;
import me.ryanhamshire.GPFlags.flags.FlagDef_PlayerWeather;
import me.ryanhamshire.GPFlags.flags.FlagDef_RaidMemberOnly;
import me.ryanhamshire.GPFlags.flags.FlagDef_RespawnLocation;
import me.ryanhamshire.GPFlags.flags.FlagDef_SpleefArena;
import me.ryanhamshire.GPFlags.flags.FlagDef_TrappedDestination;
import me.ryanhamshire.GPFlags.flags.FlagDefinition;
import me.ryanhamshire.GPFlags.listener.PlayerListener;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.MessageStore;
import me.ryanhamshire.GPFlags.message.Messages;
import me.ryanhamshire.GPFlags.metrics.Metrics;
import me.ryanhamshire.GPFlags.util.Current;
import me.ryanhamshire.GPFlags.util.Legacy;
import me.ryanhamshire.GPFlags.util.Util;
import me.ryanhamshire.GPFlags.util.VersionControl;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * <b>Main GriefPrevention Flags class</b>
 */
public class GPFlags extends JavaPlugin {
    private static VersionControl vc;

    //for convenience, a reference to the instance of this plugin
    private static GPFlags instance;

    //this handles customizable messages
    private MessageStore messageStore;

    //this handles flags
    private final FlagManager flagManager = new FlagManager();

    //this handles worldwide settings (aka global flags)
    private WorldSettingsManager worldSettingsManager = new WorldSettingsManager();

    private boolean registeredFlagDefinitions = false;
    private PlayerListener playerListener;

    private static boolean LOG_ENTER_EXIT_COMMANDS = true;

    //adds a server log entry
    public static synchronized void addLogEntry(String entry) {
    	Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&bGPFlags&7] " + entry));
    }

    public static void logFlagCommands(String log) {
        if (LOG_ENTER_EXIT_COMMANDS) {
            addLogEntry(log);
        }
    }

    public void onEnable() {
        this.messageStore = new MessageStore();
        Messages.register(this.messageStore);
    	this.playerListener = new PlayerListener();
    	Bukkit.getPluginManager().registerEvents(playerListener, this);

        int ver = Integer.parseInt(Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].split("_")[1]);

        // Check if server is running MC 1.13+ (API Changes)
        if (ver >= 13) {
            vc = new Current();
            addLogEntry(ChatColor.GREEN + "1.13+ Version Loaded");
        } else {
            vc = new Legacy();
            addLogEntry(ChatColor.GREEN + "Legacy Version Loaded");
        }

        instance = this;

        this.loadConfig();

        @SuppressWarnings("unused")
        Metrics metrics = new Metrics(this);

        addLogEntry("Boot finished.");
        if (getDescription().getVersion().contains("Beta")) {
            addLogEntry(ChatColor.YELLOW + "You are running a Beta version, things may not operate as expected");
        }
    }

    private void loadConfig() {
        this.flagManager.clear();
        this.worldSettingsManager = new WorldSettingsManager();
        this.messageStore.reload(YamlConfiguration.loadConfiguration(new File(FlagsDataStore.messagesFilePath)));

        //load the config if it exists
        FileConfiguration inConfig = YamlConfiguration.loadConfiguration(new File(FlagsDataStore.configFilePath));
        FileConfiguration outConfig = new YamlConfiguration();

        List<World> worlds = this.getServer().getWorlds();
        ArrayList<String> worldSettingsKeys = new ArrayList<>();
        for (World world : worlds) {
            worldSettingsKeys.add(world.getName());
        }
        // As a test I am removing this, I don't even know why its in here, since GPFlags supports multiverse worlds
        //worldSettingsKeys.add(this.worldSettingsManager.OtherWorldsKey);

        for (String worldName : worldSettingsKeys) {
            WorldSettings settings = this.worldSettingsManager.create(worldName);

            LOG_ENTER_EXIT_COMMANDS = inConfig.getBoolean("Settings.Log Enter/Exit Messages To Console", true);
            outConfig.set("Settings.Log Enter/Exit Messages To Console", LOG_ENTER_EXIT_COMMANDS);

            settings.worldGamemodeDefault = inConfig.getString("World Flags." + worldName + ".Default Gamemode", "survival");
            if (!settings.worldGamemodeDefault.equalsIgnoreCase("survival") && !settings.worldGamemodeDefault.equalsIgnoreCase("creative") &&
                    !settings.worldGamemodeDefault.equalsIgnoreCase("adventure") && !settings.worldGamemodeDefault.equalsIgnoreCase("spectator")) {
                settings.worldGamemodeDefault = "survival";
            }
            outConfig.set("World Flags." + worldName + ".Default Gamemode", settings.worldGamemodeDefault);

            settings.pvpRequiresClaimFlag = inConfig.getBoolean("World Flags." + worldName + ".PvP Only In PvP-Flagged Claims", false);
            outConfig.set("World Flags." + worldName + ".PvP Only In PvP-Flagged Claims", settings.pvpRequiresClaimFlag);

            settings.pvpDeniedMessage = inConfig.getString("World Flags." + worldName + ".Message To Send When PvP Is Denied",
                    "Player vs. player combat is restricted to designated areas.");
            outConfig.set("World Flags." + worldName + ".Message To Send When PvP Is Denied", settings.pvpDeniedMessage);

            settings.pvpEnterClaimMessageEnabled = inConfig.getBoolean("World Flags." + worldName + ".Send Message On Enter PvP Enabled Claim", false);
            outConfig.set("World Flags." + worldName + ".Send Message On Enter PvP Enabled Claim", settings.pvpEnterClaimMessageEnabled);

            settings.pvpEnterClaimMessage = inConfig.getString("World Flags." + worldName + ".Message",
                    "PvP is enabled in this claim, be careful");
            outConfig.set("World Flags." + worldName + ".Message", settings.pvpEnterClaimMessage);

            settings.pvpExitClaimMessageEnabled = inConfig.getBoolean("World Flags." + worldName + ".Send Message On Exit PvP Enabled Claim", false);
            outConfig.set("World Flags." + worldName + ".Send Message On Exit PvP Enabled Claim", settings.pvpExitClaimMessageEnabled);

            settings.pvpExitClaimMessage = inConfig.getString("World Flags." + worldName + ".ExitMessage", "PvP is disabled in this area, you are now safe");
            outConfig.set("World Flags." + worldName + ".ExitMessage", settings.pvpExitClaimMessage);

            // Adds default biomes to be ignored in the ChangeBiome flag
            settings.biomeBlackList = inConfig.getList("World Flags." + worldName + ".Biomes.Blacklist", getVersionControl().getDefaultBiomes());
            outConfig.set("World Flags." + worldName + ".Biomes.Blacklist", settings.biomeBlackList);

            settings.noMonsterSpawnIgnoreSpawners = inConfig.getBoolean("World Flags." + worldName + ".NoMonsterSpawn Flag Ignores Spawners and Eggs", true);
            outConfig.set("World Flags." + worldName + ".NoMonsterSpawn Flag Ignores Spawners and Eggs", settings.noMonsterSpawnIgnoreSpawners);

            outConfig.options().header("GriefPrevention Flags\n" + "Plugin Version: " + this.getDescription().getVersion() +
                    "\nServer Version: " + getServer().getVersion() + "\n\n");
        }

        try {
            outConfig.save(FlagsDataStore.configFilePath);
            addLogEntry("Finished loading configuration.");
        } catch (IOException exception) {
            addLogEntry("Unable to write to the configuration file at \"" + FlagsDataStore.configFilePath + "\"");
        }

        //register flag definitions
        if (!this.registeredFlagDefinitions) {
            this.registeredFlagDefinitions = true;
            this.flagManager.registerFlagDefinition(new FlagDef_NoMonsterSpawns(this.flagManager, this));
            FlagDef_AllowPvP allowPvPDef = new FlagDef_AllowPvP(this.flagManager, this, this.worldSettingsManager);
            allowPvPDef.firstTimeSetup();
            this.flagManager.registerFlagDefinition(allowPvPDef);
            this.flagManager.registerFlagDefinition(new FlagDef_EnterMessage(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_ExitMessage(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_EnterCommand(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_EnterPlayerCommand(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_ExitCommand(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_ExitPlayerCommand(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_RespawnLocation(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_KeepInventory(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_InfiniteArrows(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_KeepLevel(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NetherPortalPlayerCommand(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NetherPortalConsoleCommand(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoCombatLoot(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoMobSpawns(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoPlayerDamage(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoPlayerDamageByMonster(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoEnter(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoMobDamage(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoFluidFlow(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_HealthRegen(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoHunger(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_CommandWhiteList(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_CommandBlackList(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoFlight(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_TrappedDestination(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoLootProtection(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoEnderPearl(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoExpiration(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoLeafDecay(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoPetDamage(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoWeatherChange(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoItemPickup(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoChorusFruit(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_SpleefArena(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoItemDrop(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoGrowth(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_OwnerFly(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_OwnerMemberFly(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoEnterPlayer(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_PlayerWeather(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_PlayerTime(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_PlayerGamemode(this.flagManager, this, this.worldSettingsManager));
            this.flagManager.registerFlagDefinition(new FlagDef_NoVineGrowth(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoSnowForm(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoIceForm(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoFireSpread(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoFireDamage(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoFallDamage(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_EnterCommand_Owner(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_EnterCommand_Members(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_ExitCommand_Owner(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_ExitCommand_Members(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoExplosionDamage(this.flagManager, this));

            this.flagManager.registerFlagDefinition(new FlagDef_ChangeBiome(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoOpenDoors(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoVehicle(this.flagManager, this));

            this.flagManager.registerFlagDefinition(new FlagDef_NoMobSpawnsType(this.flagManager, this));
            this.flagManager.registerFlagDefinition(new FlagDef_NoItemDamage(this.flagManager, this));

            try {
            	Class.forName("org.bukkit.event.raid.RaidTriggerEvent");
            	this.flagManager.registerFlagDefinition(new FlagDef_RaidMemberOnly(this.flagManager, this));
			} catch (ClassNotFoundException e) {
            	if (Util.isRunningMinecraft(1, 14)) {
					addLogEntry("&cRaidEvent classes not found:");
					addLogEntry("&7  - Update to latest Spigot build for raid flag to work");
				}
			}

			//try to hook into mcMMO
            try {
                if (Bukkit.getPluginManager().getPlugin("mcMMO") != null) {
                    this.flagManager.registerFlagDefinition(new FlagDef_NoMcMMOSkills(this.flagManager, this));
                    this.flagManager.registerFlagDefinition(new FlagDef_NoMcMMODeathPenalty(this.flagManager, this));
                    // Experimental
                    this.flagManager.registerFlagDefinition(new FlagDef_NoMcMMOXP(this.flagManager, this));
                }
            }
            //if failed, we just won't have those flags available
            catch (NoClassDefFoundError ignore) {
            }
        } else {
            ((FlagDef_PlayerGamemode) this.flagManager.getFlagDefinitionByName("PlayerGamemode")).updateSettings(this.worldSettingsManager);
            ((FlagDef_AllowPvP) this.flagManager.getFlagDefinitionByName("AllowPvP")).updateSettings(this.worldSettingsManager);
            ((FlagDef_NoMonsterSpawns) this.flagManager.getFlagDefinitionByName("NoMonsterSpawns")).updateSettings(this.worldSettingsManager);
        }

        try {
            File flagsFile = new File(FlagsDataStore.flagsFilePath);
            List<Message> errors = this.flagManager.load(flagsFile);
            if (errors.size() > 0) {
                File errorFile = new File(FlagsDataStore.flagsErrorFilePath);
                Files.copy(flagsFile, errorFile);
                for (Message error : errors) {
                    GPFlags.addLogEntry("Load Error: " + error.getText());
                }
                GPFlags.addLogEntry("Problems encountered reading the flags data file! " +
                        "Please share this log and your 'flagsError.yml' file with the developer.");
            }
        } catch (Exception e) {
            GPFlags.addLogEntry("Unable to initialize the file system data store.  Details:");
            GPFlags.addLogEntry(e.getMessage());
            e.printStackTrace();
        }

        //drop any flags which no longer correspond to existing land claims (maybe they were deleted)
        Collection<Claim> topLevelClaims = GriefPrevention.instance.dataStore.getClaims();
        HashSet<String> validIDs = new HashSet<String>();
        for (Claim claim : topLevelClaims) {
            validIDs.add(claim.getID().toString());
            for (Claim subclaim : claim.children) {
                validIDs.add(subclaim.getID().toString());
            }
        }
        this.flagManager.removeExceptClaimIDs(validIDs);
        addLogEntry("Finished loading data.");
    }

    //handles slash commands
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = null;
        if (cmd.getName().equalsIgnoreCase("allflags")) {
            for (FlagDefinition flag : this.flagManager.getFlagDefinitions()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        flag.getName() + " &7" + flag.getFlagType()));
            }
            return true;
        }
        if (sender instanceof Player) {
            player = (Player) sender;
        } else {
            if (!cmd.getLabel().contains("server") && !cmd.getName().equalsIgnoreCase("GPFReload") &&
                    !cmd.getName().equalsIgnoreCase("SetClaimFlagPlayer")) {
                getLogger().info(ChatColor.RED + "This command can only be issued by a player");
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("GPFReload")) {
            this.loadConfig();
            sender.sendMessage(Messages.RELOAD_COMPLETE.getText(TextMode.SUCCESS));
            return true;
        } else if (cmd.getName().equalsIgnoreCase("SetDefaultClaimFlag")) {
            if (args.length < 1) return false;

            String flagName = args[0];

            FlagDefinition def = this.flagManager.getFlagDefinitionByName(flagName);
            if (def == null) {
                sender.sendMessage(Messages.INVALID_FLAG.getText(TextMode.ERROR, getFlagDefsMessage(sender)));
                return true;
            }

            if (!this.playerHasPermissionForFlag(def, player)) {
                sender.sendMessage(Messages.NO_FLAG_PERMISSION.getText(TextMode.ERROR));
                return true;
            }

            if (!def.getFlagType().contains(FlagDefinition.FlagType.CLAIM)) {
                sender.sendMessage(Messages.NO_FLAG_IN_CLAIM.getText(TextMode.ERROR));
                return true;
            }

            String[] params = new String[args.length - 1];
            System.arraycopy(args, 1, params, 0, args.length - 1);

            SetFlagResult result = this.flagManager.setFlag(FlagManager.DEFAULT_FLAG_ID, def, true, params);
            if (result.success) {
                sender.sendMessage(Messages.DEFAULT_FLAG_SET.getText(TextMode.SUCCESS));
                this.flagManager.save();
            } else {
                sender.sendMessage(result.message.getText(TextMode.ERROR, result.parameters));
            }

            return true;
        } else if (cmd.getName().equalsIgnoreCase("UnSetDefaultClaimFlag")) {
            if (args.length < 1) return false;

            String flagName = args[0];
            FlagDefinition def = this.flagManager.getFlagDefinitionByName(flagName);
            if (def == null) {
                sender.sendMessage(Messages.INVALID_FLAG.getText(TextMode.ERROR, getFlagDefsMessage(sender)));
                return true;
            }

            if (!this.playerHasPermissionForFlag(def, player)) {
                sender.sendMessage(Messages.NO_FLAG_PERMISSION.getText(TextMode.ERROR));
                return true;
            }

            SetFlagResult result = this.flagManager.unSetFlag(FlagManager.DEFAULT_FLAG_ID, def);
            if (result.success) {
                sender.sendMessage(Messages.DEFAULT_FLAG_UNSET.getText(TextMode.SUCCESS));
                this.flagManager.save();
            } else {
                sender.sendMessage(result.message.getText(TextMode.ERROR, result.parameters));
            }

            return true;
        } else if (cmd.getName().equalsIgnoreCase("SetServerFlag")) {
            if (args.length < 1) return false;

            String flagName = args[0];
            FlagDefinition def = this.flagManager.getFlagDefinitionByName(flagName);
            if (def == null) {
                sender.sendMessage(Messages.INVALID_FLAG.getText(TextMode.ERROR, getFlagDefsMessage(sender)));
                return true;
            }

            if (!this.playerHasPermissionForFlag(def, player)) {
                sender.sendMessage(Messages.NO_FLAG_PERMISSION.getText(TextMode.ERROR));
                return true;
            }

            if (!def.getFlagType().contains(FlagDefinition.FlagType.SERVER)) {
                sender.sendMessage(Messages.NO_FLAG_ON_SERVER.getText(TextMode.ERROR));
                return true;
            }

            String[] params = new String[args.length - 1];
            System.arraycopy(args, 1, params, 0, args.length - 1);

            SetFlagResult result = this.flagManager.setFlag("everywhere", def, true, params);
            if (result.success) {
                sender.sendMessage(Messages.SERVER_FLAG_SET.getText(TextMode.SUCCESS));
                this.flagManager.save();
            } else {
                sender.sendMessage(result.message.getText(TextMode.ERROR, result.parameters));
            }

            return true;
        } else if (cmd.getName().equalsIgnoreCase("UnSetServerFlag")) {
            if (args.length < 1) return false;

            String flagName = args[0];
            FlagDefinition def = this.flagManager.getFlagDefinitionByName(flagName);
            if (def == null) {
                sender.sendMessage(Messages.INVALID_FLAG.getText(TextMode.ERROR, getFlagDefsMessage(sender)));
                return true;
            }

            if (!this.playerHasPermissionForFlag(def, player)) {
                sender.sendMessage(Messages.NO_FLAG_PERMISSION.getText(TextMode.ERROR));
                return true;
            }

            SetFlagResult result = this.flagManager.unSetFlag("everywhere", def);
            if (result.success) {
                sender.sendMessage(Messages.SERVER_FLAG_UNSET.getText(TextMode.SUCCESS));
                this.flagManager.save();
            } else {
                sender.sendMessage(result.message.getText(TextMode.ERROR, result.parameters));
            }

            return true;
        }

        // Set a claimFlag for a player from console
        if (cmd.getName().equalsIgnoreCase("SetClaimFlagPlayer")) {
            if (args.length < 2) return false;
            player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(Util.clr( "&c" + args[0] + " &7is not online"));
                return false;
            }
            PlayerData playerData = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId());
            Claim claim = GriefPrevention.instance.dataStore.getClaimAt(player.getLocation(), false, playerData.lastClaim);
            if (claim == null || claim.allowEdit(player) != null) {
                sender.sendMessage(Util.clr("&cThis player is not standing in a claim they own"));
                return false;
            }

            String flagName = args[1];
            FlagDefinition def = this.flagManager.getFlagDefinitionByName(flagName);
            if (def == null) {
                sender.sendMessage(Util.clr("&c" + args[1] + "&7 is not a valid flag"));
                return false;
            }
            if (!def.getFlagType().contains(FlagDefinition.FlagType.CLAIM)) {
                sender.sendMessage(Messages.NO_FLAG_IN_CLAIM.getText(TextMode.ERROR));
                return true;
            }

            String[] params = new String[args.length - 2];
            System.arraycopy(args, 2, params, 0, args.length - 2);

            // SET BIOME
            if (flagName.equalsIgnoreCase("ChangeBiome")) {
                if (args.length < 3) return false;
                FlagDef_ChangeBiome flagD = ((FlagDef_ChangeBiome) this.flagManager.getFlagDefinitionByName("changebiome"));
                String biome = params[0].toUpperCase().replace(" ", "_");
                if (!flagD.changeBiome(sender, claim, biome)) return true;
            }

            SetFlagResult result = this.flagManager.setFlag(claim.getID().toString(), def, true, params);
            ChatColor color = result.success ? TextMode.SUCCESS : TextMode.ERROR;
            sender.sendMessage(result.message.getText(color, result.parameters));
            if (result.success) {
                this.flagManager.save();
                sender.sendMessage(Util.clr("&7Flag &b" + def.getName() + " &7successfully set in &b" + player.getName() + "&7's claim."));
                return true;
            }

            return true;
        }

        if (player == null) {
            GPFlags.addLogEntry("You must be logged into the game to use that command.");
        }

        if (cmd.getName().equalsIgnoreCase("SetWorldFlag") && player != null) {
            if (args.length < 1) return false;

            String flagName = args[0];
            FlagDefinition def = this.flagManager.getFlagDefinitionByName(flagName);
            if (def == null) {
                sender.sendMessage(Messages.INVALID_FLAG.getText(TextMode.ERROR, getFlagDefsMessage(sender)));
                return true;
            }

            if (!this.playerHasPermissionForFlag(def, player)) {
                sender.sendMessage(Messages.NO_FLAG_PERMISSION.getText(TextMode.ERROR));
                return true;
            }

            if (!def.getFlagType().contains(FlagDefinition.FlagType.WORLD)) {
                sender.sendMessage(Messages.NO_FLAG_IN_WORLD.getText(TextMode.ERROR));
                return true;
            }

            String[] params = new String[args.length - 1];
            System.arraycopy(args, 1, params, 0, args.length - 1);

            SetFlagResult result = this.flagManager.setFlag(player.getWorld().getName(), def, true, params);
            if (result.success) {
                sender.sendMessage(Messages.WORLD_FLAG_SET.getText(TextMode.SUCCESS));
                this.flagManager.save();
            } else {
                sender.sendMessage(result.message.getText(TextMode.ERROR, result.parameters));
            }

            return true;
        } else if (cmd.getName().equalsIgnoreCase("UnSetWorldFlag") && player != null) {
            if (args.length < 1) return false;

            String flagName = args[0];
            FlagDefinition def = this.flagManager.getFlagDefinitionByName(flagName);
            if (def == null) {
                sender.sendMessage(Messages.INVALID_FLAG.getText(TextMode.ERROR, getFlagDefsMessage(sender)));
                return true;
            }

            if (!this.playerHasPermissionForFlag(def, player)) {
                sender.sendMessage(Messages.NO_FLAG_PERMISSION.getText(TextMode.ERROR));
                return true;
            }

            SetFlagResult result = this.flagManager.unSetFlag(player.getWorld().getName(), def);
            if (result.success) {
                sender.sendMessage(Messages.WORLD_FLAG_UNSET.getText(TextMode.SUCCESS));
                this.flagManager.save();
            } else {
                sender.sendMessage(result.message.getText(TextMode.ERROR, result.parameters));
            }

            return true;
        } else if (cmd.getName().equalsIgnoreCase("ListClaimFlags") && player != null) {
            PlayerData playerData = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId());

            Claim claim = GriefPrevention.instance.dataStore.getClaimAt(player.getLocation(), false, playerData.lastClaim);

            Collection<Flag> flags;
            boolean flagsFound = false;
            StringBuilder builder1 = new StringBuilder();
            StringBuilder builder2 = new StringBuilder();
            StringBuilder builder3 = new StringBuilder();
            if (claim != null) {
                flags = this.flagManager.getFlags(claim.getID().toString());
                for (Flag flag : flags) {
                    flagsFound = true;
                    builder1.append(flag.getSet() ? "+" : "-").append(flag.flagDefinition.getName()).append(" ");
                }

                if (claim.parent != null) {
                    flags = this.flagManager.getFlags(claim.parent.getID().toString());
                    for (Flag flag : flags) {
                        flagsFound = true;
                        builder2.append(flag.getSet() ? "+" : "-").append(flag.flagDefinition.getName()).append(" ");
                    }
                }

                flags = this.flagManager.getFlags(FlagManager.DEFAULT_FLAG_ID);
                for (Flag flag2 : flags) {
                    flagsFound = true;
                    builder3.append(flag2.getSet() ? "+" : "-").append(flag2.flagDefinition.getName()).append(" ");
                }
            }

            StringBuilder builder4 = new StringBuilder();
            flags = this.flagManager.getFlags(player.getWorld().getName());
            for (Flag flag3 : flags) {
                flagsFound = true;
                builder4.append(flag3.getSet() ? "+" : "-").append(flag3.flagDefinition.getName()).append(" ");
            }

            StringBuilder builder5 = new StringBuilder();
            flags = this.flagManager.getFlags("everywhere");
            for (Flag flag4 : flags) {
                flagsFound = true;
                builder5.append(flag4.getSet() ? "+" : "-").append(flag4.flagDefinition.getName()).append(" ");
            }

            if (builder1.length() > 0)
                sender.sendMessage(Messages.FLAGS_CLAIM.getText(TextMode.INFO, builder1.toString()));
            if (builder2.length() > 0)
                sender.sendMessage(Messages.FLAGS_PARENT.getText(TextMode.INFO, builder2.toString()));
            if (builder3.length() > 0)
                sender.sendMessage(Messages.FLAGS_DEFAULT.getText(TextMode.INFO, builder3.toString()));
            if (builder4.length() > 0)
                sender.sendMessage(Messages.FLAGS_WORLD.getText(TextMode.INFO, builder4.toString()));
            if (builder5.length() > 0)
                sender.sendMessage(Messages.FLAGS_SERVER.getText(TextMode.INFO, builder5.toString()));

            if (!flagsFound) {
                sender.sendMessage(Messages.NO_FLAGS_HERE.getText(TextMode.INFO));
            }

            return true;
        }

        PlayerData playerData = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId());
        Claim claim = GriefPrevention.instance.dataStore.getClaimAt(player.getLocation(), false, playerData.lastClaim);

        if (claim == null) {
            sender.sendMessage(Messages.STAND_IN_CLAIM.getText(TextMode.ERROR));
            return true;
        }

        Long claimID = claim.getID();
        if (claimID == null || claimID == -1) {
            sender.sendMessage(Messages.UPDATE_SUBDIVISION_FLAGS.getText(TextMode.ERROR));
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("SetClaimFlag")) {
            if (args.length < 1) return false;

            String flagName = args[0];
            FlagDefinition def = this.flagManager.getFlagDefinitionByName(flagName);
            if (def == null) {
                sender.sendMessage(Messages.INVALID_FLAG.getText(TextMode.ERROR, getFlagDefsMessage(sender)));
                return true;
            }

            if (!this.playerHasPermissionForFlag(def, player)) {
                sender.sendMessage(Messages.NO_FLAG_PERMISSION.getText(TextMode.ERROR));
                return true;
            }

            if (!def.getFlagType().contains(FlagDefinition.FlagType.CLAIM)) {
                sender.sendMessage(Messages.NO_FLAG_IN_CLAIM.getText(TextMode.ERROR));
                return true;
            }

            if (claim.allowEdit(player) != null) {
                sender.sendMessage(Messages.NOT_YOUR_CLAIM.getText(TextMode.ERROR));
                return true;
            }

            String[] params = new String[args.length - 1];
            System.arraycopy(args, 1, params, 0, args.length - 1);

            // stop owner/ownermember fly flags from joining
            Collection<Flag> flags;
            flags = this.flagManager.getFlags(claim.getID().toString());
            for (Flag flag : flags) {
                if (args[0].equalsIgnoreCase("OwnerFly")) {
                    if (flag.flagDefinition.getName().equalsIgnoreCase("OwnerMemberFly")) {
                        sender.sendMessage(Messages.FLIGHT_NO_OWNER_FLAG.getText(TextMode.WARNING));
                        return true;
                    }
                }
                if (args[0].equalsIgnoreCase("OwnerMemberFly")) {
                    if (flag.flagDefinition.getName().equalsIgnoreCase("OwnerFly")) {
                        sender.sendMessage(Messages.FLIGHT_NO_OWNER_FLAG.getText(TextMode.WARNING));
                        return true;
                    }
                }
            }

            // SET BIOME
            if (flagName.equalsIgnoreCase("ChangeBiome")) {
                if (args.length < 2) return false;
                FlagDef_ChangeBiome flagD = ((FlagDef_ChangeBiome) this.flagManager.getFlagDefinitionByName("changebiome"));
                String biome = params[0].toUpperCase().replace(" ", "_");
                if (!flagD.changeBiome(sender, claim, biome)) return true;
            }

            // Permissions for mob type
            if (flagName.equalsIgnoreCase("NoMobSpawnsType")) {
                if (!player.hasPermission("gpflags.nomobspawnstype.*") && !player.hasPermission("gpflags.admin.*")) {
                    for (String type : params[0].split(";")) {
                        if (!player.hasPermission("gpflags.nomobspawnstype." + type)) {
                            sender.sendMessage(Messages.MOB_TYPE_PERMISSION.getText(TextMode.ERROR));
                            return true;
                        }
                    }
                }
            }

            SetFlagResult result = this.flagManager.setFlag(claimID.toString(), def, true, params);
            ChatColor color = result.success ? TextMode.SUCCESS : TextMode.ERROR;
            sender.sendMessage(result.message.getText(color, result.parameters));
            if (result.success) this.flagManager.save();

            return true;
        } else if (cmd.getName().equalsIgnoreCase("UnSetClaimFlag")) {
            if (args.length < 1) return false;

            String flagName = args[0];

            FlagDefinition def = this.flagManager.getFlagDefinitionByName(flagName);
            if (def == null) {
                sender.sendMessage(Messages.INVALID_FLAG.getText(TextMode.ERROR, getFlagDefsMessage(sender)));
                return true;
            }

            if (!this.playerHasPermissionForFlag(def, player)) {
                sender.sendMessage(Messages.NO_FLAG_PERMISSION.getText(TextMode.ERROR));
                return true;
            }

            if (claim.allowEdit(player) != null) {
                sender.sendMessage(Messages.NOT_YOUR_CLAIM.getText(TextMode.ERROR));
                return true;
            }

            // TODO RESET BIOME
            if (flagName.equalsIgnoreCase("ChangeBiome")) {
                FlagDef_ChangeBiome flagD = ((FlagDef_ChangeBiome) this.flagManager.getFlagDefinitionByName("changebiome"));
                flagD.resetBiome(claim.getID());
            }

            SetFlagResult result = this.flagManager.unSetFlag(claimID.toString(), def);
            ChatColor color = result.success ? TextMode.SUCCESS : TextMode.ERROR;
            sender.sendMessage(result.message.getText(color));
            if (result.success) this.flagManager.save();

            return true;
        }

        return false;
    }

    private boolean playerHasPermissionForFlag(FlagDefinition flagDef, Permissible player) {
        String flagName = flagDef.getName();
        return player == null || player.hasPermission("gpflags.allflags") || player.hasPermission("gpflags." +
                flagName) || player.hasPermission("gpflags." + flagName.toLowerCase());
    }

    //sends a color-coded message to a player
    public static void sendMessage(CommandSender sender, ChatColor color, String message) {
        if (message == null || message.length() == 0) return;

        if (sender == null) {
            GPFlags.addLogEntry(color + message);
        } else {
            sender.sendMessage(color + message);
        }
    }

    // Messages.INVALID_FLAG
    private String getFlagDefsMessage(Permissible player) {
        StringBuilder flagDefsList = new StringBuilder();
        Collection<FlagDefinition> defs = this.flagManager.getFlagDefinitions();
        for (FlagDefinition def : defs) {
            if (this.playerHasPermissionForFlag(def, player)) {
                flagDefsList.append(def.getName()).append(" ");
            }
        }

        return flagDefsList.toString();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) throws IllegalArgumentException {
        return this.flagManager.onTabComplete(sender, command, alias, args);
    }

    /** Get an instance of this plugin
     * @return Instance of this plugin
     */
    public static GPFlags getInstance() {
        return instance;
    }

    /** Get an instance of the flag manager
     * @return Instance of the flag manager
     */
    public FlagManager getFlagManager() {
        return this.flagManager;
    }

    /** Get an instance of the world settings manager
     * @return Instance of the world settings manager
     */
    public WorldSettingsManager getWorldSettingsManager() {
        return this.worldSettingsManager;
    }

    /** Get an instance of the version control class
     * @return Instance of the version control class
     */
    public VersionControl getVersionControl() {
        return vc;
    }

    /** Get an instance of the player listener class
     * @return Instance of the player listener class
     */
    public PlayerListener getPlayerListener() {
    	return this.playerListener;
	}

	public MessageStore getMessageStore() {
        return messageStore;
    }

}
