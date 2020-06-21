package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoPetDamage extends FlagDefinition {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Tameable)) return;

        Tameable tameable = (Tameable) entity;
        if (!tameable.isTamed() || tameable.getOwner() == null) return;

        Flag flag = this.GetFlagInstanceAtLocation(entity.getLocation(), null);
        if (flag != null) {
            event.setCancelled(true);
        }
    }

    public FlagDef_NoPetDamage(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoPetDamage";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NO_PET_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_PET_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
