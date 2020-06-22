package me.ryanhamshire.GPFlags.message;

import org.bukkit.ChatColor;

public interface Message {
    String getId();

    String getText();

    String getNotes();

    void setText(String text);

    void setNotes(String node);

    default String getText(String... notes) {
        String text = getText();
        for (int i = 0; i < notes.length; i++) {
            String param = notes[i];
            text = text.replace("{" + i + "}", param);
        }
        return text;
    }

    default String getText(ChatColor color, String... notes) {
        return color + getText(notes);
    }

}
