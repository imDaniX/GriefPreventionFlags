package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Arrays;
import java.util.List;

public class FlagDef_KeepLevel extends FlagDefinition {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        Flag flag = this.GetFlagInstanceAtLocation(player.getLocation(), null);
        if (flag == null) return;

        event.setKeepLevel(true);
        event.setDroppedExp(0);
    }

    public FlagDef_KeepLevel(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "KeepLevel";
    }

    @Override
	public Message getSetMessage() {
        return Messages.KEEP_LEVEL_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.KEEP_INVENTORY_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
