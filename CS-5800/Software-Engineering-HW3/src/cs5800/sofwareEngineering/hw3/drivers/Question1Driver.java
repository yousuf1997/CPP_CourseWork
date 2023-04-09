package cs5800.sofwareEngineering.hw3.drivers;

import cs5800.sofwareEngineering.hw3.model.q1.*;

public class Question1Driver {
    public static void main(String[] args) {
        // version 1 with limited functionalities
        MessagingApplication facebookMessagingV1 = new FacebookMessaging("V1",new TextMessage(), new ImageMessage());
        MessagingApplication whatsAppMessagingV1 = new WhatsAppMessaging("V1",new TextMessage());
        MessagingApplication telegramMessagingV1 = new TelegramMessaging("V1",new TextMessage());
        
        // send text message
        facebookMessagingV1.sendMessage(MessageTypeEnum.TEXT);
        whatsAppMessagingV1.sendMessage(MessageTypeEnum.TEXT);
        telegramMessagingV1.sendMessage(MessageTypeEnum.TEXT);

        // send image message
        facebookMessagingV1.sendMessage(MessageTypeEnum.IMAGE);
        // not supported by the following message mediums
        whatsAppMessagingV1.sendMessage(MessageTypeEnum.IMAGE);
        telegramMessagingV1.sendMessage(MessageTypeEnum.IMAGE);

        // version 2 with multi mediums
        MessagingApplication facebookMessagingV2 = new FacebookMessaging("V2", new TextMessage(), new ImageMessage(), new VideoMessage());
        MessagingApplication whatsAppMessagingV2 = new WhatsAppMessaging("V2", new TextMessage(), new ImageMessage());
        MessagingApplication telegramMessagingV2 = new TelegramMessaging("V2", new TextMessage(), new ImageMessage());

        // send image message
        facebookMessagingV2.sendMessage(MessageTypeEnum.IMAGE);
        whatsAppMessagingV2.sendMessage(MessageTypeEnum.IMAGE);
        telegramMessagingV2.sendMessage(MessageTypeEnum.IMAGE);

        // send video messages
        facebookMessagingV2.sendMessage(MessageTypeEnum.VIDEO);
        // not supported by the following message mediums
        whatsAppMessagingV2.sendMessage(MessageTypeEnum.VIDEO);
        telegramMessagingV2.sendMessage(MessageTypeEnum.VIDEO);

        // version 3 with all type of messaging
        MessagingApplication whatsAppMessagingV3 = new WhatsAppMessaging("V3", new TextMessage(), new ImageMessage(), new VideoMessage());
        MessagingApplication telegramMessagingV3 = new TelegramMessaging("V3", new TextMessage(), new ImageMessage(), new VideoMessage());

        // send video messages
        facebookMessagingV2.sendMessage(MessageTypeEnum.VIDEO);
        whatsAppMessagingV3.sendMessage(MessageTypeEnum.VIDEO);
        telegramMessagingV3.sendMessage(MessageTypeEnum.VIDEO);
    }
}
