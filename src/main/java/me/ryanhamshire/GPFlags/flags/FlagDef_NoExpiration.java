package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import me.ryanhamshire.GriefPrevention.events.ClaimExpirationEvent;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.Collections;
import java.util.List;

public class FlagDef_NoExpiration extends FlagDefinition
{
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClaimExpiration(ClaimExpirationEvent event)
    {
        Location location = event.getClaim().getLesserBoundaryCorner();
        Flag flag = this.GetFlagInstanceAtLocation(location, null);
        if(flag != null)
        {
            event.setCancelled(true);
        }
    }
    
    public FlagDef_NoExpiration(FlagManager manager, GPFlags plugin)
    {
        super(manager, plugin);
    }
    
    @Override
    public String getName()
    {
        return "NoExpiration";
    }

    @Override
	public Message getSetMessage()
    {
        return Messages.NO_EXPIRATION_ENABLE;
    }

    @Override
    public Message getUnSetMessage()
    {
        return Messages.NO_EXPIRATION_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Collections.singletonList(FlagType.CLAIM);
    }

}
