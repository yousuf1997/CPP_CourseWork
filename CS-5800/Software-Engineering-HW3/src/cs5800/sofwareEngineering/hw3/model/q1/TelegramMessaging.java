package cs5800.sofwareEngineering.hw3.model.q1;

import java.util.Arrays;

public class TelegramMessaging extends MessagingApplication {

    public TelegramMessaging(String version, MessageType ...messageTypes) {
        this.messageTypes = Arrays.asList(messageTypes);
        this.version = version;
    }

    @Override
    public void sendMessage(MessageTypeEnum messageType) {
        MessageType messageSender = this.messageTypes.stream().filter(messageMedium -> messageMedium
                        .getMessageType().equals(messageType))
                .findFirst()
                .orElse(null);
        if (messageSender == null) {
            System.out.println(messageType + " messaging is not supported by TelegramMessaging (" + version +")");
            return;
        }
        System.out.println("TelegramMessaging Sending... (" + version +")");
        messageSender.sendMessage();
    }
}
