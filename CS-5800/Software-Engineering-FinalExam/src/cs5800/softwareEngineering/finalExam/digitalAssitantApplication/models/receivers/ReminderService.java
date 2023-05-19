package cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers;

public class ReminderService implements Receiver {
    public void setReminder(String reminderText) {
        System.out.println("The System is currently setting following reminder : " + reminderText);
    }
}
