package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.LeavesDecayEvent;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoLeafDecay extends FlagDefinition {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeafDecay(LeavesDecayEvent event) {
        Block block = event.getBlock();

        Flag flag = this.GetFlagInstanceAtLocation(block.getLocation(), null);
        if (flag == null) return;

        event.setCancelled(true);
    }

    public FlagDef_NoLeafDecay(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoLeafDecay";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NO_LEAF_DECAY_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_LEAF_DECAY_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
