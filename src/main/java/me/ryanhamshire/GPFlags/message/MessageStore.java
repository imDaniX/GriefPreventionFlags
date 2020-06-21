package me.ryanhamshire.GPFlags.message;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class MessageStore {
    private final Map<String, Message> messages;

    public MessageStore() {
        messages = new HashMap<>();
    }

    public void register(Message message) {
        messages.put(message.getId(), message);
    }

    public String getText(String id, String... notes) {
        return messages.get(id).getText(notes);
    }

    public String getText(String id, ChatColor color, String... notes) {
        return messages.get(id).getText(color, notes);
    }

    public Message getMessageObject(String id) {
        return messages.get(id);
    }

    public void reload(ConfigurationSection cfg) {
        ConfigurationSection msgCfg = cfg.getConfigurationSection("Messages");
        messages.values().forEach(m -> {
            m.setText(msgCfg.getString(m.getId() + ".Text"));
            m.setNote(msgCfg.getString(m.getId() + ".Note"));
        });
    }
}
