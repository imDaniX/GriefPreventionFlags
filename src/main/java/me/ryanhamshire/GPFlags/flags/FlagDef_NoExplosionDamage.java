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

public class FlagDef_NoExplosionDamage extends FlagDefinition {

    @EventHandler
    public void onFall(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION ||
        e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {

            Flag flag = this.GetFlagInstanceAtLocation(e.getEntity().getLocation(), null);
            if (flag == null) return;

            e.setCancelled(true);
        }
    }

    public FlagDef_NoExplosionDamage(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoExplosionDamage";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NO_EXPLOSION_DAMAGE_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_EXPLOSION_DAMAGE_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
