package cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.template;

import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.command.Command;

public abstract class RequestProcessorTemplate {
    public final void processRequest(Command command) {
        validateInput(command);
        logRequest(command);
        executeCommand(command);
        notifyUser(command);
    }

    protected abstract void validateInput(Command command);

    protected abstract void logRequest(Command command);

    protected abstract void executeCommand(Command command);

    protected abstract void notifyUser(Command command);
}
