package cs5800.sofwareEngineering.hw3.model.q1;

public class VideoMessage implements MessageType {
    @Override
    public void sendMessage() {
        System.out.println("Sending video message");
    }

    @Override
    public MessageTypeEnum getMessageType() {
        return MessageTypeEnum.VIDEO;
    }
}
