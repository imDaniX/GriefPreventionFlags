package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.*;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class FlagDef_EnterCommand_Members extends PlayerMovementFlagDefinition {

    @Override
    public boolean allowMovement(Player player, Location lastLocation, Location to)
    {
        if(lastLocation == null) return true;
        Flag flag = this.GetFlagInstanceAtLocation(to, player);
        if(flag == null) return true;

        if(flag == this.GetFlagInstanceAtLocation(lastLocation, player)) return true;

        PlayerData playerData = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId());
        Claim claim = GriefPrevention.instance.dataStore.getClaim(playerData.lastClaim.getID());
        if (claim.allowAccess(player) == null) return true;
        String [] commandLines = flag.parameters.replace("%name%", player.getName()).replace("%uuid%", player.getUniqueId().toString()).split(";");
        for(String commandLine : commandLines)
        {
            GPFlags.logFlagCommands("Entrance command: " + commandLine);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), commandLine);
        }

        return true;
    }

    public FlagDef_EnterCommand_Members(FlagManager manager, GPFlags plugin)
    {
        super(manager, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Flag flag = this.GetFlagInstanceAtLocation(player.getLocation(), player);
        if(flag == null) return;
        PlayerData playerData = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId());
        Claim claim = GriefPrevention.instance.dataStore.getClaim(playerData.lastClaim.getID());
        if (claim.allowAccess(player) == null) return;
        String [] commandLines = flag.parameters.replace("%name%", player.getName()).replace("%uuid%", player.getUniqueId().toString()).split(";");

        for(String commandLine : commandLines) {
            GPFlags.logFlagCommands("Entrance command: " + commandLine);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), commandLine);
        }
    }

    @Override
    public String getName()
    {
        return "EnterCommand-Members";
    }

    @Override
    public SetFlagResult ValidateParameters(String parameters)
    {
        if(parameters.isEmpty())
        {
            return new SetFlagResult(false, new MessageSpecifier(Messages.ConsoleCommandRequired));
        }

        return new SetFlagResult(true, this.getSetMessage(parameters));
    }

    @Override
    public MessageSpecifier getSetMessage(String parameters)
    {
        return new MessageSpecifier(Messages.AddedEnterCommand, parameters);
    }

    @Override
    public MessageSpecifier getUnSetMessage()
    {
        return new MessageSpecifier(Messages.RemovedEnterCommand);
    }
}
