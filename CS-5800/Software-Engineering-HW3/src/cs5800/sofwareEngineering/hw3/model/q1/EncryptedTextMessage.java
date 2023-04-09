package cs5800.sofwareEngineering.hw3.model.q1;

public class EncryptedTextMessage implements MessageType {
    @Override
    public void sendMessage() {
        System.out.println("Sent encrypted text message.");
    }

    @Override
    public MessageTypeEnum getMessageType() {
        return MessageTypeEnum.ENCRYPTED_TEXT;
    }
}
