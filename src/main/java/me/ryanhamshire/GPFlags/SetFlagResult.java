package me.ryanhamshire.GPFlags;

import me.ryanhamshire.GPFlags.message.Message;

public class SetFlagResult {

    private final boolean success;
    private final Message message;
    private final String parameters;

    public SetFlagResult(boolean success, Message message, String parameters) {
        this.success = success;
        this.message = message;
        this.parameters = parameters;
    }

    public SetFlagResult(boolean success, Message message) {
        this.success = success;
        this.message = message;
        this.parameters = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public Message getMessage() {
        return message;
    }
    public String getParameters() {
        return parameters;
    }
}
