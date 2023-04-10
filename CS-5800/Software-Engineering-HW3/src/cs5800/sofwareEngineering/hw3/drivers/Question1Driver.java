package cs5800.sofwareEngineering.hw3.drivers;

import cs5800.sofwareEngineering.hw3.model.q1.*;
import cs5800.sofwareEngineering.hw3.model.q1.message.EncryptedTextMessage;
import cs5800.sofwareEngineering.hw3.model.q1.message.ImageMessage;
import cs5800.sofwareEngineering.hw3.model.q1.message.TextMessage;
import cs5800.sofwareEngineering.hw3.model.q1.message.VideoMessage;
import cs5800.sofwareEngineering.hw3.model.q1.messagemedium.FacebookMessaging;
import cs5800.sofwareEngineering.hw3.model.q1.messagemedium.MessagingApplication;
import cs5800.sofwareEngineering.hw3.model.q1.messagemedium.TelegramMessaging;
import cs5800.sofwareEngineering.hw3.model.q1.messagemedium.WhatsAppMessaging;

public class Question1Driver {
    public static void main(String[] args) {
        // version 1 with very specific functionalities
        MessagingApplication facebookMessagingV1 = new FacebookMessaging("V1", new ImageMessage());
        MessagingApplication whatsAppMessagingV1 = new WhatsAppMessaging("V1",new TextMessage());
        MessagingApplication telegramMessagingV1 = new TelegramMessaging("V1",new VideoMessage());

        // send text message on whatsApp
        whatsAppMessagingV1.sendMessage(MessageTypeEnum.TEXT);
        // send video message on Telegram
        telegramMessagingV1.sendMessage(MessageTypeEnum.VIDEO);
        // send image message on facebook
        facebookMessagingV1.sendMessage(MessageTypeEnum.IMAGE);

        // version 2 with limited functionalities
        MessagingApplication facebookMessagingV2 = new FacebookMessaging("V2",new TextMessage(), new ImageMessage());
        MessagingApplication whatsAppMessagingV2 = new WhatsAppMessaging("V2",new TextMessage());
        MessagingApplication telegramMessagingV2 = new TelegramMessaging("V2",new TextMessage());

        // send text message
        facebookMessagingV2.sendMessage(MessageTypeEnum.TEXT);
        whatsAppMessagingV2.sendMessage(MessageTypeEnum.TEXT);
        telegramMessagingV2.sendMessage(MessageTypeEnum.TEXT);

        // send image message
        facebookMessagingV2.sendMessage(MessageTypeEnum.IMAGE);
        // not supported by the following message mediums
        whatsAppMessagingV2.sendMessage(MessageTypeEnum.IMAGE);
        telegramMessagingV2.sendMessage(MessageTypeEnum.IMAGE);

        // version 2 with multi mediums
        MessagingApplication facebookMessagingV3 = new FacebookMessaging("V3", new TextMessage(), new ImageMessage(), new VideoMessage());
        MessagingApplication whatsAppMessagingV3 = new WhatsAppMessaging("V3", new TextMessage(), new ImageMessage());
        MessagingApplication telegramMessagingV3 = new TelegramMessaging("V3", new TextMessage(), new ImageMessage());

        // send image message
        facebookMessagingV3.sendMessage(MessageTypeEnum.IMAGE);
        whatsAppMessagingV3.sendMessage(MessageTypeEnum.IMAGE);
        telegramMessagingV3.sendMessage(MessageTypeEnum.IMAGE);

        // send video messages
        facebookMessagingV3.sendMessage(MessageTypeEnum.VIDEO);
        // not supported by the following message mediums
        whatsAppMessagingV3.sendMessage(MessageTypeEnum.VIDEO);
        telegramMessagingV3.sendMessage(MessageTypeEnum.VIDEO);

        // version 3 with all type of messaging
        MessagingApplication whatsAppMessagingV4 = new WhatsAppMessaging("V4", new TextMessage(), new ImageMessage(), new VideoMessage());
        MessagingApplication telegramMessagingV4 = new TelegramMessaging("V4", new TextMessage(), new ImageMessage(), new VideoMessage());

        // send video messages
        facebookMessagingV3.sendMessage(MessageTypeEnum.VIDEO);
        whatsAppMessagingV4.sendMessage(MessageTypeEnum.VIDEO);
        telegramMessagingV4.sendMessage(MessageTypeEnum.VIDEO);

        // version 4 of whatsapp message supports encrypted message
        MessagingApplication whatsAppMessagingV5 = new WhatsAppMessaging("V5", new EncryptedTextMessage() ,new TextMessage(), new ImageMessage(), new VideoMessage());

        // send encrypted message
        whatsAppMessagingV5.sendMessage(MessageTypeEnum.ENCRYPTED_TEXT);
        // following mediums do not support encrypted message
        facebookMessagingV3.sendMessage(MessageTypeEnum.ENCRYPTED_TEXT);
        telegramMessagingV4.sendMessage(MessageTypeEnum.ENCRYPTED_TEXT);

    }
}
