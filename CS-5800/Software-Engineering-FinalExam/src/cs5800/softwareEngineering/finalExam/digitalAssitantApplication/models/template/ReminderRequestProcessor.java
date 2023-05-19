package cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.template;

import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.command.Command;
import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.command.ReminderCommand;

public class ReminderRequestProcessor  extends RequestProcessorTemplate {
    @Override
    protected void validateInput(Command command) {
        if (command instanceof ReminderCommand) {
            return;
        }
        throw new IllegalArgumentException("The user has issued invalid command, only Email Request Command can be processed.");
    }

    @Override
    protected void logRequest(Command command) {
        System.out.println("The System is logging the reminder request..");
    }

    @Override
    protected void executeCommand(Command command) {
        System.out.println("The System is currently about to execute the command.");
        command.execute();
    }

    @Override
    protected void notifyUser(Command command) {
        System.out.println("The system is currently notifying user about reminder");
    }
}
