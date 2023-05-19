package cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.command;

import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers.EmailSender;
import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers.Receiver;

public class EmailCommand implements Command {
    private final EmailSender emailSender;
    private final String recipient;
    private final String message;

    public EmailCommand(Receiver emailSender, String recipient, String message) {
        this.emailSender = (EmailSender) emailSender;
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public void execute() {
        emailSender.sendEmail(recipient, message);
    }
}
