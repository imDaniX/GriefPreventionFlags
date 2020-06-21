package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.SetFlagResult;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class FlagDef_NoHunger extends TimedPlayerFlagDefinition {

    private ConcurrentHashMap<UUID, Integer> lastFoodMap = new ConcurrentHashMap<>();

    @Override
    public long getPlayerCheckFrequency_Ticks() {
        return 100L;
    }

    @Override
    public void processPlayer(Player player) {
        if (player.getFoodLevel() >= 20) return;

        UUID playerID = player.getUniqueId();
        Flag flag = this.GetFlagInstanceAtLocation(player.getLocation(), player);
        if (flag != null) {
            Integer lastFoodLevel = this.lastFoodMap.get(playerID);
            if (lastFoodLevel != null && player.getFoodLevel() < lastFoodLevel) {
                player.setFoodLevel(lastFoodLevel);
            }

            int healAmount = 0;
            if (flag.parameters != null && !flag.parameters.isEmpty()) {
                try {
                    healAmount = Integer.parseInt(flag.parameters);
                } catch (NumberFormatException e) {
                    GPFlags.addLogEntry("Problem with hunger level regen amount @ " + player.getLocation().getBlock().getLocation().toString());
                }
            }

            int newFoodLevel = healAmount + player.getFoodLevel();
            player.setFoodLevel((Math.min(20, newFoodLevel)));
            player.setSaturation(player.getFoodLevel());
        }

        this.lastFoodMap.put(playerID, player.getFoodLevel());
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getCause() != DamageCause.STARVATION) return;
        if (event.getEntityType() != EntityType.PLAYER) return;
        Player player = (Player) event.getEntity();
        Flag flag = this.GetFlagInstanceAtLocation(player.getLocation(), player);
        if (flag == null) return;

        event.setCancelled(true);
        player.setFoodLevel(player.getFoodLevel() + 1);
        player.setSaturation(player.getFoodLevel());
    }

    public FlagDef_NoHunger(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoHunger";
    }

    @Override
    public SetFlagResult ValidateParameters(String parameters) {
        if (!parameters.isEmpty()) {
            int amount;
            try {
                amount = Integer.parseInt(parameters);
                if (amount < 0) {
                    return new SetFlagResult(false, Messages.NO_HUNGER_INVALID);
                }
            } catch (NumberFormatException e) {
                return new SetFlagResult(false, Messages.NO_HUNGER_INVALID);
            }
        } else {
            return new SetFlagResult(false, Messages.NO_HUNGER_INVALID);
        }

        return new SetFlagResult(true, this.getSetMessage(), parameters);
    }

    @Override
    public Message getSetMessage() {
        return Messages.NO_HUNGER_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_HUNGER_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
