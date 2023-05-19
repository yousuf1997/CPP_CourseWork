package cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.command;

import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers.PhoneDialer;
import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers.Receiver;

public class PhoneCallCommand implements Command {
    private final PhoneDialer phoneDialer;
    private final String phoneNumber;

    public PhoneCallCommand(Receiver phoneDialer, String phoneNumber) {
        this.phoneDialer = (PhoneDialer) phoneDialer;
        this.phoneNumber = phoneNumber;
    }

    public void execute() {
        phoneDialer.makeCall(phoneNumber);
    }
}
