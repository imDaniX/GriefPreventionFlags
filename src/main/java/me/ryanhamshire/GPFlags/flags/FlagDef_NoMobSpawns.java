package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoMobSpawns extends FlagDefinition {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) return;

        if (event.getLocation() == null) return;

        EntityType type = event.getEntityType();
        if (type == EntityType.PLAYER) return;
        if (type == EntityType.ARMOR_STAND) return;

        SpawnReason reason = event.getSpawnReason();
        if (reason == SpawnReason.SPAWNER || reason == SpawnReason.SPAWNER_EGG) return;


        Flag flag = this.GetFlagInstanceAtLocation(event.getLocation(), null);
        if (flag == null) return;

        event.setCancelled(true);
    }

    public FlagDef_NoMobSpawns(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoMobSpawns";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NO_MOB_SPAWNS_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_MOB_SPAWNS_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.WORLD, FlagType.CLAIM, FlagType.SERVER);
    }
}
