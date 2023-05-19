package cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.template;

import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.command.Command;
import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.command.MusicCommand;

public class MusicRequestProcessor extends RequestProcessorTemplate {
    @Override
    protected void validateInput(Command command) {
        if (command instanceof MusicCommand) {
            return;
        }
        throw new IllegalArgumentException("The user has issued invalid command, only Music Request Command can be processed.");
    }

    @Override
    protected void logRequest(Command command) {
        System.out.println("The System is logging the music request..");
    }

    @Override
    protected void executeCommand(Command command) {
        System.out.println("The System is currently about to execute the command.");
        command.execute();
    }

    @Override
    protected void notifyUser(Command command) {
        System.out.println("The system is currently notifying user about music playback");
    }
}
