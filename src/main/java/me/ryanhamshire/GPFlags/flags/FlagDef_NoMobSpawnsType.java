package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.SetFlagResult;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoMobSpawnsType extends FlagDefinition {

	private final String ALLOW_TARGET_TAG = "GPF_AllowTarget";

	@EventHandler
	private void onMobSpawn(CreatureSpawnEvent event) {
		Flag flag = this.GetFlagInstanceAtLocation(event.getLocation(), null);
		if (flag == null) return;

		EntityType type = event.getEntityType();
		if (type == EntityType.PLAYER) return;
		if (type == EntityType.ARMOR_STAND) return;

		if (isNotAllowed(type, flag)) {
            CreatureSpawnEvent.SpawnReason reason = event.getSpawnReason();
            if (reason == CreatureSpawnEvent.SpawnReason.SPAWNER || reason == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) {
                event.getEntity().setMetadata(this.ALLOW_TARGET_TAG, new FixedMetadataValue(GPFlags.getInstance(), Boolean.TRUE));
                return;
            }
            event.setCancelled(true);
        }
	}

	@EventHandler
	private void onMobTarget(EntityTargetEvent event) {
		Entity target = event.getTarget();
		Entity damager = event.getEntity();

		if (target == null) return;

		Flag flag = this.GetFlagInstanceAtLocation(target.getLocation(), null);
		if (flag == null) return;
		if (isNotAllowed(damager.getType(), flag)) {
            if (damager.hasMetadata(this.ALLOW_TARGET_TAG)) return;
            event.setCancelled(true);
            damager.remove();
        }
	}

	@EventHandler
	private void onMobDamage(EntityDamageByEntityEvent event) {
		Entity target = event.getEntity();
		Entity damager = event.getDamager();

		if (damager instanceof Player) return;
		if (!(damager instanceof LivingEntity)) return;
		if (!(target instanceof Player)) return;

		Flag flag = this.GetFlagInstanceAtLocation(target.getLocation(), null);
		if (flag == null) return;
        if (isNotAllowed(damager.getType(), flag)) {
            if (damager.hasMetadata(this.ALLOW_TARGET_TAG)) return;
            event.setCancelled(true);
            damager.remove();
        }
	}

	public FlagDef_NoMobSpawnsType(FlagManager manager, GPFlags plugin) {
		super(manager, plugin);
	}

	@Override
	public SetFlagResult ValidateParameters(String parameters) {
		if (parameters.isEmpty()) {
			return new SetFlagResult(false, Messages.MOB_TYPE_REQUIRED);
		}
		return new SetFlagResult(true, this.getSetMessage(), parameters.replace(";", ", "));
	}

	@Override
	public String getName() {
		return "NoMobSpawnsType";
	}

	@Override
	public Message getSetMessage() {
		return Messages.NO_MOB_SPAWNS_TYPES_ENABLE;
	}

	@Override
	public Message getUnSetMessage() {
		return Messages.NO_MOB_SPAWNS_TYPES_DISABLE;
	}

	@Override
	public List<FlagType> getFlagType() {
		return Arrays.asList(FlagType.WORLD, FlagType.CLAIM, FlagType.SERVER);
	}

	private boolean isNotAllowed(EntityType type, Flag flag) {
        for (String t : flag.parameters.split(";")) {
            if (t.equalsIgnoreCase(type.toString())) return true;
        }
        return false;
    }

}
