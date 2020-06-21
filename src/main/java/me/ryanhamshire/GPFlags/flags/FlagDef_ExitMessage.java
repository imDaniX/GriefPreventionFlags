package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.SetFlagResult;
import me.ryanhamshire.GPFlags.TextMode;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FlagDef_ExitMessage extends PlayerMovementFlagDefinition {

    private String prefix;

    public FlagDef_ExitMessage(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
        this.prefix = Messages.ENTER_EXIT_PREFIX.getText();
    }

    @Override
    public boolean allowMovement(Player player, Location lastLocation, Location to) {
        if (lastLocation == null) return true;
        Flag flag = this.GetFlagInstanceAtLocation(lastLocation, player);
        if (flag == null) return true;

        Claim claim = GriefPrevention.instance.dataStore.getClaimAt(lastLocation, true, null);
        String message = flag.parameters;
        if (claim != null) {
            message = message.replace("%owner%", claim.getOwnerName()).replace("%name%", player.getName());
        }

        GPFlags.sendMessage(player, TextMode.INFO, prefix + message);

        return true;
    }

    @Override
    public String getName() {
        return "ExitMessage";
    }

    @Override
    public SetFlagResult ValidateParameters(String parameters)
    {
        if(parameters.isEmpty())
        {
            return new SetFlagResult(false, Messages.MESSAGE_REQUIRED);
        }

        return new SetFlagResult(true, this.getSetMessage(), parameters);
    }

    @Override
    public Message getSetMessage()
    {
        return Messages.EXIT_MESSAGE_ADDED;
    }

    @Override
    public Message getUnSetMessage()
    {
        return Messages.EXIT_MESSAGE_REMOVED;
    }

}
