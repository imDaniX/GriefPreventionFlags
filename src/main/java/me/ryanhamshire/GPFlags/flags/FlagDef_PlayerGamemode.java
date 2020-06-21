package me.ryanhamshire.GPFlags.flags;


import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.SetFlagResult;
import me.ryanhamshire.GPFlags.TextMode;
import me.ryanhamshire.GPFlags.WorldSettings;
import me.ryanhamshire.GPFlags.WorldSettingsManager;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FlagDef_PlayerGamemode extends PlayerMovementFlagDefinition implements Listener {

    private WorldSettingsManager settingsManager;

    @Override
    public boolean allowMovement(Player player, Location lastLocation, Location to)
    {
        WorldSettings settings = this.settingsManager.get(player.getWorld());

        if(lastLocation == null) return true;
        Flag flag = this.GetFlagInstanceAtLocation(to, player);
        if(flag == null) {
            if(this.GetFlagInstanceAtLocation(lastLocation, player) == null) return true;

            String gameMode = settings.worldGamemodeDefault;
            player.setGameMode(GameMode.valueOf(gameMode.toUpperCase()));
            GPFlags.sendMessage(player, TextMode.WARNING, Messages.PLAYER_GAMEMODE_MESSAGE.getText(gameMode));

            if(player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
                Block block = player.getLocation().getBlock();
                Block below = block.getRelative(BlockFace.DOWN);
                if(below.getRelative(BlockFace.DOWN).getType() != Material.AIR && block.getRelative(BlockFace.UP).getType() == Material.AIR) return true;
                while(block.getY() > 2 && !block.getType().isSolid() && block.getType() != Material.WATER) {
                    block = block.getRelative(BlockFace.DOWN);
                }
                player.teleport(block.getRelative(BlockFace.UP).getLocation());
            }
            return true;
        }
        if(flag == this.GetFlagInstanceAtLocation(lastLocation, player)) return true;
        String gameMode = flag.parameters;
        String playerGameMode = player.getGameMode().toString();
        if(gameMode.equalsIgnoreCase(playerGameMode)) return true;
        player.setGameMode(GameMode.valueOf(gameMode.toUpperCase()));
        GPFlags.sendMessage(player, TextMode.WARNING, Messages.PLAYER_GAMEMODE_MESSAGE.getText(gameMode));
        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Flag flag = this.GetFlagInstanceAtLocation(player.getLocation(), player);
        if(flag != null) {
            String gameMode = flag.parameters;
            player.setGameMode(GameMode.valueOf(gameMode.toUpperCase()));
        }
    }

    public FlagDef_PlayerGamemode(FlagManager manager, GPFlags plugin, WorldSettingsManager settingsManager) {
        super(manager, plugin);
        this.settingsManager = settingsManager;
    }

    @Override
    public SetFlagResult ValidateParameters(String parameters)
    {
        if(parameters.isEmpty())
        {
            return new SetFlagResult(false, Messages.PLAYER_GAMEMODE_REQUIRED);
        }
        if(!parameters.equalsIgnoreCase("survival") && !parameters.equalsIgnoreCase("creative") &&
                !parameters.equalsIgnoreCase("adventure") && !parameters.equalsIgnoreCase("spectator")) {
            return new SetFlagResult(false, Messages.PLAYER_GAMEMODE_REQUIRED);
        }
        return new SetFlagResult(true, this.getSetMessage(), parameters);
    }

    public void updateSettings(WorldSettingsManager settingsManager) {
        this.settingsManager = settingsManager;
    }

    @Override
    public String getName() {
        return "PlayerGamemode";
    }

    @Override
	public Message getSetMessage() {
        return Messages.PLAYER_GAMEMODE_SET;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.PLAYER_GAMEMODE_UNSET;
    }

}
