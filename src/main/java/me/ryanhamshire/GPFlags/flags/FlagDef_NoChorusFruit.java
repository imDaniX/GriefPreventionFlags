package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import java.util.Collections;
import java.util.List;

public class FlagDef_NoChorusFruit extends FlagDefinition {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (event.getCause() != TeleportCause.CHORUS_FRUIT) return;

        Flag flag = this.GetFlagInstanceAtLocation(event.getFrom(), event.getPlayer());
        if (flag != null) {
            event.setCancelled(true);
        }

        flag = this.GetFlagInstanceAtLocation(event.getTo(), event.getPlayer());
        if (flag != null) {
            event.setCancelled(true);
        }
    }

    public FlagDef_NoChorusFruit(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoChorusFruit";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NO_CHORUS_FRUIT_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_CHORUS_FRUIT_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Collections.singletonList(FlagType.CLAIM);
    }

}
