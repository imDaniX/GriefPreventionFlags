package me.ryanhamshire.GPFlags.message;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

public interface CustomizableMessage {
    String getText();

    String getNote();

    void load(ConfigurationSection cfg);

    static String clr(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
