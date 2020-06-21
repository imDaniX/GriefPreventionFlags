package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.SetFlagResult;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FlagDef_PlayerTime extends PlayerMovementFlagDefinition implements Listener {

    @Override
    public boolean allowMovement(Player player, Location lastLocation, Location to)
    {
        if(lastLocation == null) return true;
        Flag flag = this.GetFlagInstanceAtLocation(to, player);

        if(flag == null) {
            if (this.GetFlagInstanceAtLocation(lastLocation, player) != null) {
                player.resetPlayerTime();
            }
            return true;
        }
        if(flag == this.GetFlagInstanceAtLocation(lastLocation, player)) return true;

        String time = flag.parameters;
        if(time.equalsIgnoreCase("day")) {
            player.setPlayerTime(0, false);
        } else if(time.equalsIgnoreCase("noon")) {
            player.setPlayerTime(6000, false);
        } else if(time.equalsIgnoreCase("night")) {
            player.setPlayerTime(12566, false);
        } else if(time.equalsIgnoreCase("midnight")) {
            player.setPlayerTime(18000, false);
        }
        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Flag flag = this.GetFlagInstanceAtLocation(player.getLocation(), player);

        if(flag != null) {
            String time = flag.parameters;
            if (time.equalsIgnoreCase("day")) {
                player.setPlayerTime(0, false);
            } else if (time.equalsIgnoreCase("noon")) {
                player.setPlayerTime(6000, false);
            } else if (time.equalsIgnoreCase("night")) {
                player.setPlayerTime(12566, false);
            } else if (time.equalsIgnoreCase("midnight")) {
                player.setPlayerTime(18000, false);
            }
        }
    }

    public FlagDef_PlayerTime(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public SetFlagResult ValidateParameters(String parameters)
    {
        if(parameters.isEmpty())
        {
            return new SetFlagResult(false, Messages.PLAYER_TIME_REQUIRED);
        }
        if(!parameters.equalsIgnoreCase("day") && !parameters.equalsIgnoreCase("noon") &&
        !parameters.equalsIgnoreCase("night") && !parameters.equalsIgnoreCase("midnight")) {
            return new SetFlagResult(false, Messages.PLAYER_TIME_REQUIRED);
        }
        return new SetFlagResult(true, this.getSetMessage(), parameters);
    }

    @Override
    public String getName() {
        return "PlayerTime";
    }

    @Override
	public Message getSetMessage() {
        return Messages.PLAYER_TIME_SET;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.PLAYER_TIME_UNSET;
    }

}
