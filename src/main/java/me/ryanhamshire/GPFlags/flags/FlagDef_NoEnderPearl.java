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
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import java.util.Collections;
import java.util.List;

public class FlagDef_NoEnderPearl extends FlagDefinition {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (event.getCause() != TeleportCause.ENDER_PEARL) return;

        Player player = event.getPlayer();

        Flag flag = this.GetFlagInstanceAtLocation(event.getFrom(), event.getPlayer());
        if (flag != null) {
            event.setCancelled(true);
            Claim claim = GriefPrevention.instance.dataStore.getClaimAt(event.getFrom(), true, null);
            if (claim != null) {
                String owner = claim.getOwnerName();

                String msg = Messages.NO_ENDERPEARL_IN.getText();
                GPFlags.sendMessage(player, TextMode.WARNING, msg.replace("{o}", owner).replace("{p}", player.getName()));
                return;
            }
            String msg = Messages.NO_ENDERPEARL_WORLD.getText();
            GPFlags.sendMessage(player, TextMode.WARNING, msg.replace("{p}", player.getName()));
            return;
        }

        flag = this.GetFlagInstanceAtLocation(event.getTo(), event.getPlayer());
        if (flag != null) {
            event.setCancelled(true);
            Claim claim = GriefPrevention.instance.dataStore.getClaimAt(event.getTo(), true, null);
            if (claim != null) {
                String owner = claim.getOwnerName();

                String msg = Messages.NO_ENDERPEARL_TO.getText();
                GPFlags.sendMessage(player, TextMode.WARNING, msg.replace("{o}", owner).replace("{p}", player.getName()));
            }
        }

    }

    public FlagDef_NoEnderPearl(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoEnderPearl";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NO_ENDERPEARL_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_ENDERPEARL_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Collections.singletonList(FlagType.CLAIM);
    }

}
