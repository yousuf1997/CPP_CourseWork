package cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers;

public class EmailSender implements Receiver {
    public void sendEmail(String recipient, String message) {
        if (!recipient.contains("@")) {
            throw new RuntimeException("Invalid email addresses.");
        }
        System.out.println("Sending email To the following recipient : " + recipient);
        System.out.println("Recipient of the email will receive the following message : " + message);
    }
}
