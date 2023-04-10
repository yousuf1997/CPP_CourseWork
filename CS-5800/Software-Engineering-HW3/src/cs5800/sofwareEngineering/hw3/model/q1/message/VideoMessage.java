package cs5800.sofwareEngineering.hw3.model.q1.message;

import cs5800.sofwareEngineering.hw3.model.q1.MessageTypeEnum;
import cs5800.sofwareEngineering.hw3.model.q1.message.MessageType;

public class VideoMessage implements MessageType {
    @Override
    public void sendMessage() {
        System.out.println("Sent video message");
    }

    @Override
    public MessageTypeEnum getMessageType() {
        return MessageTypeEnum.VIDEO;
    }
}
