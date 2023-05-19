package cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.driver;

import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.command.*;
import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers.*;
import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.template.*;

public class DigitalAssistantApplicationDriver {
    public static void main(String[] args) {
        Receiver phoneDialer = new PhoneDialer();
        Receiver emailSender = new EmailSender();
        Receiver musicPlayer = new MusicPlayer();
        Receiver reminderService = new ReminderService();
        RequestProcessorTemplate emailRequestProcessor = new EmailRequestProcessor();
        RequestProcessorTemplate musicRequestProcessor = new MusicRequestProcessor();
        RequestProcessorTemplate phoneCallRequestProcessor = new PhoneCallRequestProcessor();
        RequestProcessorTemplate reminderRequestProcessor = new ReminderRequestProcessor();

        Command sendEmail1 = new EmailCommand(emailSender, "jhonPatric@gmail.com", "Welcome!");
        emailRequestProcessor.processRequest(sendEmail1);
        Command sendEmail2 = new EmailCommand(emailSender, "jacobJackson@gmail.com", "Superman!");
        emailRequestProcessor.processRequest(sendEmail2);

        Command playMusic1 = new MusicCommand(musicPlayer, "Set Fire to the rain");
        musicRequestProcessor.processRequest(playMusic1);
        Command playMusic2 = new MusicCommand(musicPlayer, "It will rain");
        musicRequestProcessor.processRequest(playMusic2);

        Command makeCall1 = new PhoneCallCommand(phoneDialer, "2215298562");
        phoneCallRequestProcessor.processRequest(makeCall1);
        Command makeCall2 = new PhoneCallCommand(phoneDialer, "3652541500");
        phoneCallRequestProcessor.processRequest(makeCall2);

        Command packagePickupReminder = new ReminderCommand(reminderService, "Please pickup package from the Amazon locker.");
        reminderRequestProcessor.processRequest(packagePickupReminder);
        Command buyGroceryReminder = new ReminderCommand(reminderService, "Please buy groceries from Walmart.");
        reminderRequestProcessor.processRequest(buyGroceryReminder);
    }
}
