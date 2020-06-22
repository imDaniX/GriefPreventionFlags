package me.ryanhamshire.GPFlags.message;

import me.ryanhamshire.GPFlags.GPFlags;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
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

    public void reload(File cfgFile) {
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);

        boolean changes = false;
        for(Message msg : messages.values()) {
            String path = "Messages." + msg.getId();
            String str;

            str = cfg.getString(path + ".Text");
            msg.setText(str);
            if(str == null) {
                changes = true;
                cfg.set(path + ".Text", msg.getText());
            }

            str = cfg.getString(path + ".Notes");
            msg.setNotes(str);
            if(str == null && msg.getNotes() != null) {
                changes = true;
                cfg.set(path + ".Notes", msg.getNotes());
            }
        }

        if(changes) try {
            cfg.save(cfgFile);
        } catch (IOException e) {
            GPFlags.addLogEntry("Unable to write to the configuration file at \"" + cfgFile.getPath() + "\"");
        }
    }
}
