package cs5800.sofwareEngineering.hw3.model.q1.message;

import cs5800.sofwareEngineering.hw3.model.q1.MessageTypeEnum;

public interface MessageType {
    void sendMessage();
    MessageTypeEnum getMessageType();
}
