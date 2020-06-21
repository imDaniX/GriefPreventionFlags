package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoFireDamage extends FlagDefinition {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onFireSpread(BlockBurnEvent e) {
        Block fire = e.getBlock();
        Flag flag = this.GetFlagInstanceAtLocation(fire.getLocation(), null);
        if (flag == null) return;

        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockIgnite(BlockIgniteEvent e) {
        Block fire = e.getBlock();
        Flag flag = this.GetFlagInstanceAtLocation(fire.getLocation(), null);
        if (flag == null) return;
        if (e.getCause() == BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL) return;

        e.setCancelled(true);
    }

    public FlagDef_NoFireDamage(FlagManager manager, GPFlags plugin)
    {
        super(manager, plugin);
    }

    @Override
    public String getName()
    {
        return "NoFireDamage";
    }

    @Override
	public Message getSetMessage()
    {
        return Messages.NO_FIRE_DAMAGE_ENABLE;
    }

    @Override
    public Message getUnSetMessage()
    {
        return Messages.NO_FIRE_DAMAGE_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
