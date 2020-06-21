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
import me.ryanhamshire.GriefPrevention.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FlagDef_NoEnter extends PlayerMovementFlagDefinition
{
    @Override
    public boolean allowMovement(Player player, Location lastLocation, Location to)
    {
        if(player.hasPermission("gpflags.bypass")) return true;

        Location from = lastLocation;
        
        Flag flag = this.GetFlagInstanceAtLocation(to, player);
        if(flag == null) return true;
        
        if(from == null || flag == this.GetFlagInstanceAtLocation(from, player)) return true;
        
        PlayerData playerData = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId());
        Claim claim = GriefPrevention.instance.dataStore.getClaimAt(to, false, playerData.lastClaim);
        if(claim.allowAccess(player) != null)
        {
            GPFlags.sendMessage(player, TextMode.ERROR, flag.parameters);
            return false;
        }
        
        return true;
    }
    
    public FlagDef_NoEnter(FlagManager manager, GPFlags plugin)
    {
        super(manager, plugin);
    }
    
    @Override
    public String getName()
    {
        return "NoEnter";
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
        return Messages.NO_ENTER_ENABLE;
    }

    @Override
    public Message getUnSetMessage()
    {
        return Messages.NO_ENTER_DISABLE;
    }
}
