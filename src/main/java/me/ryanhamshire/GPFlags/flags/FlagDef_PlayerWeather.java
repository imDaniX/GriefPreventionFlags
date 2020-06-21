package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.SetFlagResult;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.Location;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FlagDef_PlayerWeather extends PlayerMovementFlagDefinition implements Listener {

    @Override
    public boolean allowMovement(Player player, Location lastLocation, Location to)
    {
        if(lastLocation == null) return true;
        Flag flag = this.GetFlagInstanceAtLocation(to, player);
        if(flag == null) {
            if (this.GetFlagInstanceAtLocation(lastLocation, player) != null) {
                player.resetPlayerWeather();
            }
            return true;
        }

        if(flag == this.GetFlagInstanceAtLocation(lastLocation, player)) return true;

        String weather = flag.parameters;
        if(weather.equalsIgnoreCase("sun")) {
            player.setPlayerWeather(WeatherType.CLEAR);
        } else if(weather.equalsIgnoreCase("rain")) {
            player.setPlayerWeather(WeatherType.DOWNFALL);
        }
        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Flag flag = this.GetFlagInstanceAtLocation(player.getLocation(), player);

        if(flag != null) {
            String weather = flag.parameters;
            if(weather.equalsIgnoreCase("sun")) {
                player.setPlayerWeather(WeatherType.CLEAR);
            } else if(weather.equalsIgnoreCase("rain")) {
                player.setPlayerWeather(WeatherType.DOWNFALL);
            }
        }
    }

    public FlagDef_PlayerWeather(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public SetFlagResult ValidateParameters(String parameters)
    {
        if(parameters.isEmpty())
        {
            return new SetFlagResult(false, Messages.PLAYER_WEATHER_REQUIRED);
        }
        if(!parameters.equalsIgnoreCase("sun") && !parameters.equalsIgnoreCase("rain")) {
            return new SetFlagResult(false, Messages.PLAYER_WEATHER_REQUIRED);
        }
        return new SetFlagResult(true, this.getSetMessage(), parameters);
    }

    @Override
    public String getName() {
        return "PlayerWeather";
    }

    @Override
	public Message getSetMessage() {
        return Messages.PLAYER_WEATHER_SET;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.PLAYER_WEATHER_UNSET;
    }

}
