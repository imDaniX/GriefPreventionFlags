package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoWeatherChange extends FlagDefinition {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent event) {
        this.handleEvent(event.getWorld(), event);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChange(ThunderChangeEvent event) {
        this.handleEvent(event.getWorld(), event);
    }

    private void handleEvent(World world, Cancellable event) {
        Flag flag = this.GetFlagInstanceAtLocation(world.getSpawnLocation(), null);
        if (flag == null) return;

        event.setCancelled(true);
    }

    public FlagDef_NoWeatherChange(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoWeatherChange";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NO_WEATHER_CHANGE_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_WEATHER_CHANGE_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.WORLD, FlagType.SERVER);
    }
}
