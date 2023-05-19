package cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers;

public class PhoneDialer implements Receiver {
    public void makeCall(String phoneNumber) {
        if (phoneNumber.length() > 10) {
            throw new RuntimeException("Invalid Phone Number Size");
        }
        System.out.println("System is currently dialing or making call for " + phoneNumber);
    }
}
