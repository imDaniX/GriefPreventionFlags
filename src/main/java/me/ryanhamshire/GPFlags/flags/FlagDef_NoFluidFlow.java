package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoFluidFlow extends FlagDefinition {

    private Location previousLocation = null;
    private boolean previousWasCancelled = false;

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockFromTo(BlockFromToEvent event) {
        Location location = event.getBlock().getLocation();
        if (this.previousLocation != null && location.equals(this.previousLocation)) {
            if (!this.previousWasCancelled) return;
            event.setCancelled(true);
            return;
        }

        Flag flag = this.GetFlagInstanceAtLocation(location, null);
        boolean cancel = (flag != null);

        this.previousLocation = location;
        this.previousWasCancelled = cancel;
        if (cancel) {
            event.setCancelled(true);
        }
    }

    public FlagDef_NoFluidFlow(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoFluidFlow";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NO_FLUID_FLOW_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_FLUID_FLOW_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
