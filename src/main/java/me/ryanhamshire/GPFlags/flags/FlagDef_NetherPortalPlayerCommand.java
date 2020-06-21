package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.SetFlagResult;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NetherPortalPlayerCommand extends FlagDefinition {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        TeleportCause cause = event.getCause();

        if (cause != TeleportCause.NETHER_PORTAL) return;

        Player player = event.getPlayer();

        Flag flag = this.GetFlagInstanceAtLocation(event.getFrom(), player);
        if (flag == null) return;

        event.setCancelled(true);
        player.performCommand(flag.parameters);
    }

    public FlagDef_NetherPortalPlayerCommand(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public SetFlagResult ValidateParameters(String parameters) {
        if (parameters.isEmpty()) {
            return new SetFlagResult(false, Messages.COMMAND_REQUIRED);
        }

        return new SetFlagResult(true, this.getSetMessage(), parameters);
    }

    @Override
    public String getName() {
        return "NetherPortalPlayerCommand";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NETHER_PORTAL_PLAYER_COMMAND_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NETHER_PORTAL_PLAYER_COMMAND_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD);
    }

}
