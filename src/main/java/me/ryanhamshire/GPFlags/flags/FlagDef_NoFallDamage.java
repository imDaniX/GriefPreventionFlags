package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoFallDamage extends FlagDefinition {

    @EventHandler
    public void onFall(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) return;

        Flag flag = this.GetFlagInstanceAtLocation(e.getEntity().getLocation(), null);
        if (flag == null) return;

        e.setCancelled(true);
    }

    public FlagDef_NoFallDamage(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoFallDamage";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NO_FALL_DAMAGE_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_FALL_DAMAGE_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
