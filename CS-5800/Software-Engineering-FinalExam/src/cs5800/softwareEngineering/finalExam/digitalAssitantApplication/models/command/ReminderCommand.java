package cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.command;

import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers.Receiver;
import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers.ReminderService;

public class ReminderCommand implements Command {
    private final ReminderService reminderService;
    private final String reminderText;

    public ReminderCommand(Receiver reminderService, String reminderText) {
        this.reminderService = (ReminderService) reminderService;
        this.reminderText = reminderText;
    }

    public void execute() {
        reminderService.setReminder(reminderText);
    }
}
