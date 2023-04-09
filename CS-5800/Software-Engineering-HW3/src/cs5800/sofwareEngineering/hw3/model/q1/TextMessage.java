package cs5800.sofwareEngineering.hw3.model.q1;

public class TextMessage implements MessageType {
    @Override
    public void sendMessage() {
        System.out.println("Sending text message.");
    }

    @Override
    public MessageTypeEnum getMessageType() {
        return MessageTypeEnum.TEXT;
    }
}
