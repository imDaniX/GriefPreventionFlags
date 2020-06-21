package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemDamageEvent;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoItemDamage extends FlagDefinition {

	@EventHandler
	private void onItemDamage(PlayerItemDamageEvent event) {
		Flag flag = this.GetFlagInstanceAtLocation(event.getPlayer().getLocation(), null);
		if (flag == null) return;
		event.setCancelled(true);
	}

	public FlagDef_NoItemDamage(FlagManager manager, GPFlags plugin) {
		super(manager, plugin);
	}

	@Override
	public String getName() {
		return "NoItemDamage";
	}

	@Override
	public Message getSetMessage() {
		return Messages.NO_ITEM_DAMAGE_ENABLE;
	}

	@Override
	public Message getUnSetMessage() {
		return Messages.NO_ITEM_DAMAGE_DISABLE;
	}

	@Override
	public List<FlagType> getFlagType() {
		return Arrays.asList(FlagType.WORLD, FlagType.CLAIM, FlagType.SERVER);
	}
}
