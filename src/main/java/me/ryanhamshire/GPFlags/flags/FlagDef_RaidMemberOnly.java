package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.TextMode;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import java.util.List;

public class FlagDef_RaidMemberOnly extends FlagDefinition {

	@EventHandler
	private void onRaidTrigger(RaidTriggerEvent event) {
		Flag flag = this.GetFlagInstanceAtLocation(event.getPlayer().getLocation(), null);
		if (flag == null) return;
		Player player = event.getPlayer();
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(player.getLocation(), false, null);
		if (claim == null) return;
		if (claim.allowAccess(player) != null) {
			event.setCancelled(true);
			player.removePotionEffect(PotionEffectType.BAD_OMEN);
			GPFlags.sendMessage(player, TextMode.WARNING, Messages.RAID_MEMBERS_DENY.getText());
		}
	}

	public FlagDef_RaidMemberOnly(FlagManager manager, GPFlags plugin) {
		super(manager, plugin);
	}

	@Override
	public String getName() {
		return "RaidMemberOnly";
	}

	@Override
	public Message getSetMessage() {
		return Messages.RAID_MEMBERS_ENABLE;
	}

	@Override
	public Message getUnSetMessage() {
		return Messages.RAID_MEMBER_DISABLE;
	}

	@Override
	public List<FlagType> getFlagType() {
		return Collections.singletonList(FlagType.CLAIM);
	}

}
