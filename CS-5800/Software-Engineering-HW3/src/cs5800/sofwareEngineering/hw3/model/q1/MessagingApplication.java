package cs5800.sofwareEngineering.hw3.model.q1;

import java.util.List;

public abstract class MessagingApplication {
    protected List<MessageType> messageTypes;
    protected String version;
    public abstract void sendMessage(MessageTypeEnum messageType);
}
