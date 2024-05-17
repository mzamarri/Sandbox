package commands;

import messages.Message;

public class PrintCommand extends Command {

    public PrintCommand(Message message) {
        super(message);
        this.isReversible = false;
    }

    public void execute() {
        message.printMessage();
    }

    public void unexecute() {

    }
}