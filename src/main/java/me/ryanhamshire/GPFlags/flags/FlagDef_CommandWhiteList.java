package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.TextMode;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.List;

public class FlagDef_CommandWhiteList extends CommandListFlagDefinition {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("gpflags.bypass")) return;

        Flag flag = this.GetFlagInstanceAtLocation(player.getLocation(), player);
        if (flag == null) return;

        if (!this.commandInList(flag.parameters, event.getMessage())) {
            event.setCancelled(true);
            player.sendMessage(Messages.COMMAND_BLOCKED_HERE.getText(TextMode.ERROR));
        }
    }

    public FlagDef_CommandWhiteList(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "CommandWhiteList";
    }

    @Override
	public Message getSetMessage() {
        return Messages.COMMAND_WHITELIST_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.COMMAND_WHITELIST_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
