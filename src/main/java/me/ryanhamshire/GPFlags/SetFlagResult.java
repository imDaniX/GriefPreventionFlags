package me.ryanhamshire.GPFlags;

import me.ryanhamshire.GPFlags.message.Message;

public class SetFlagResult {

    boolean success;
    Message message;
    String parameters;

    public SetFlagResult(boolean success, Message message, String parameters) {
        this.success = success;
        this.message = message;
        this.parameters = parameters;
    }

    public SetFlagResult(boolean success, Message message) {
        this.success = success;
        this.message = message;
    }
}
