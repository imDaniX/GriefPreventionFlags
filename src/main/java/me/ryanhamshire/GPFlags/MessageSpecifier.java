package me.ryanhamshire.GPFlags;

@Deprecated
public class MessageSpecifier {

    Messages messageID;
    String[] messageParams;

    public MessageSpecifier(Messages messageID, String... messageParams) {
        this.messageID = messageID;
        this.messageParams = messageParams;
    }

}
