package cs5800.sofwareEngineering.hw3.model.q1.messagemedium;

import cs5800.sofwareEngineering.hw3.model.q1.MessageTypeEnum;
import cs5800.sofwareEngineering.hw3.model.q1.message.MessageType;

import java.util.Arrays;

public class FacebookMessaging extends MessagingApplication {

    public FacebookMessaging(String version, MessageType...messageTypes) {
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
            System.out.println(messageType + " messaging is not supported by FacebookMessaging (" + version +")");
            return;
        }
        System.out.println("FacebookMessaging Sending... (" + version +")");
        messageSender.sendMessage();
    }
}
