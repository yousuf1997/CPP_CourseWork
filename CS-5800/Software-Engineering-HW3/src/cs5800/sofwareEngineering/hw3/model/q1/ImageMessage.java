package cs5800.sofwareEngineering.hw3.model.q1;

public class ImageMessage implements MessageType {
    @Override
    public void sendMessage() {
        System.out.println("Sent image message.");
    }

    @Override
    public MessageTypeEnum getMessageType() {
        return MessageTypeEnum.IMAGE;
    }
}
