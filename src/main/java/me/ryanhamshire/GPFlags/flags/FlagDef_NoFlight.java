package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.TextMode;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoFlight extends TimedPlayerFlagDefinition {

    @Override
    public long getPlayerCheckFrequency_Ticks() {
        return 30L;
    }

    @Override
    public void processPlayer(Player player) {
        if (!player.isFlying()) return;
        if (player.hasPermission("gpflags.bypass") || player.hasPermission("gpflags.bypass.fly")) return;

        Flag flag = this.GetFlagInstanceAtLocation(player.getLocation(), player);
        if (flag == null) return;

        Flag ownerFly = GPFlags.getInstance().getFlagManager().getFlagDefinitionByName("OwnerFly").GetFlagInstanceAtLocation(player.getLocation(), player);
        Flag ownerMember = GPFlags.getInstance().getFlagManager().getFlagDefinitionByName("OwnerMemberFly").GetFlagInstanceAtLocation(player.getLocation(), player);
        Claim claim = GriefPrevention.instance.dataStore.getClaimAt(player.getLocation(), false, null);
        if (ownerFly != null && claim.ownerID.toString().equalsIgnoreCase(player.getUniqueId().toString())) return;
        if (ownerMember != null && claim.allowAccess(player) == null) return;

        GPFlags.sendMessage(player, TextMode.ERROR, Messages.NO_FLIGHT.getText());
        player.setFlying(false);
        GameMode mode = player.getGameMode();
        if (mode != GameMode.CREATIVE && mode != GameMode.SPECTATOR) {
            Block block = player.getLocation().getBlock();
            while (block.getY() > 2 && !block.getType().isSolid() && block.getType() != Material.WATER) {
                block = block.getRelative(BlockFace.DOWN);
            }

            player.setAllowFlight(false);
            if (player.getLocation().getY() - block.getY() >= 4) {
                GPFlags.getInstance().getPlayerListener().addFallingPlayer(player);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.isFlying()) return;
        if (player.hasPermission("gpflags.bypass") || player.hasPermission("gpflags.bypass.fly")) return;

        Flag ownerFly = GPFlags.getInstance().getFlagManager().getFlagDefinitionByName("OwnerFly").GetFlagInstanceAtLocation(player.getLocation(), player);
        Flag ownerMember = GPFlags.getInstance().getFlagManager().getFlagDefinitionByName("OwnerMemberFly").GetFlagInstanceAtLocation(player.getLocation(), player);
        if (ownerFly != null || ownerMember != null) return;

        Flag flag = this.GetFlagInstanceAtLocation(player.getLocation(), player);
        if (flag == null) return;

        GPFlags.sendMessage(player, TextMode.ERROR, Messages.NO_FLIGHT.getText());
        event.setCancelled(true);
        player.setAllowFlight(false);
    }

    public FlagDef_NoFlight(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoFlight";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NO_FLIGHT_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_FLIGHT_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
