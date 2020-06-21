package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.SetFlagResult;
import me.ryanhamshire.GPFlags.TextMode;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import me.ryanhamshire.GPFlags.util.VersionControl;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Collections;
import java.util.List;

public class FlagDef_NoOpenDoors extends FlagDefinition {

	private VersionControl vc = GPFlags.getInstance().getVersionControl();

	@EventHandler
	public void onDoorOpen(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = e.getClickedBlock();
			assert block != null;
			Flag flag = this.GetFlagInstanceAtLocation(block.getLocation(), player);
			if (flag == null) return;

			if (vc.isOpenable(block)) {

				PlayerData playerData = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId());
				Claim claim = GriefPrevention.instance.dataStore.getClaimAt(block.getLocation(), true, playerData.lastClaim);

				String[] params = null;
				if (!flag.parameters.isEmpty()) {
					params = flag.parameters.split(",");
				}

				if (claim.allowAccess(player) != null) {
					if (params != null) {
						for (String param : params) {
							if (param.equalsIgnoreCase("doors") && vc.isDoor(block)) {
								e.setCancelled(true);
								GPFlags.sendMessage(player, TextMode.ERROR, Messages.NO_OPEN_DOOR_MESSAGE.getText(param));
							}
							if (param.equalsIgnoreCase("trapdoors") && vc.isTrapDoor(block)) {
								e.setCancelled(true);
								GPFlags.sendMessage(player, TextMode.ERROR, Messages.NO_OPEN_DOOR_MESSAGE.getText(param));
							}
							if (param.equalsIgnoreCase("gates") && vc.isGate(block)) {
								e.setCancelled(true);
								GPFlags.sendMessage(player, TextMode.ERROR, Messages.NO_OPEN_DOOR_MESSAGE.getText(param));
							}
						}
					} else {
						e.setCancelled(true);
						GPFlags.sendMessage(player, TextMode.ERROR, Messages.NO_OPEN_DOOR_MESSAGE.getText("doors"));
					}
				}
			}
		}
	}

	public FlagDef_NoOpenDoors(FlagManager manager, GPFlags plugin) {
		super(manager, plugin);
	}

	@Override
	public String getName() {
		return "NoOpenDoors";
	}

	@Override
	public SetFlagResult ValidateParameters(String parameters) {
		if (parameters.isEmpty()) {
			return new SetFlagResult(false, this.getSetMessage());
		}
		return new SetFlagResult(true, this.getSetMessage(), parameters);
	}

	@Override
	public Message getSetMessage() {
		return Messages.NO_OPEN_DOOR_ENABLE;
	}

	@Override
	public Message getUnSetMessage() {
		return Messages.NO_OPEN_DOOR_DISABLE;
	}

	@Override
	public List<FlagType> getFlagType() {
		return Collections.singletonList(FlagType.CLAIM);
	}

}
