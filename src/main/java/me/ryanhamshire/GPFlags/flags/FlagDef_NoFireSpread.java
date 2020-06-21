package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockSpreadEvent;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoFireSpread extends FlagDefinition {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onFireSpread(BlockSpreadEvent e)
    {
        Block fire = e.getSource();
        if(fire.getType() != Material.FIRE) return;

        Flag flag = this.GetFlagInstanceAtLocation(fire.getLocation(), null);
        if(flag == null) return;

        e.setCancelled(true);
    }

    public FlagDef_NoFireSpread(FlagManager manager, GPFlags plugin)
    {
        super(manager, plugin);
    }

    @Override
    public String getName()
    {
        return "NoFireSpread";
    }

    @Override
	public Message getSetMessage()
    {
        return Messages.NO_FIRE_SPREAD_ENABLE;
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
