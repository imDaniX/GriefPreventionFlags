package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import me.ryanhamshire.GriefPrevention.events.ProtectDeathDropsEvent;
import org.bukkit.event.EventHandler;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoLootProtection extends FlagDefinition {

    @EventHandler
    public void onPlayerDeath(ProtectDeathDropsEvent event) {
        if (event.getClaim() != null) {
            Flag flag = this.GetFlagInstanceAtLocation(event.getClaim().getLesserBoundaryCorner(), null);
            if (flag == null) return;

            event.setCancelled(true);
        }
    }

    public FlagDef_NoLootProtection(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoLootProtection";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NO_LOOT_PROTECTION_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_LOOT_PROTECTION_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
