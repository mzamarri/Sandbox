package commands;

import messages.*;

public class SaveCommand extends Command {

    public SaveCommand(Message message) {
        super(message);
        this.isReversible = false;
    }
    public void execute() {
        System.out.println("Saving...");
        message.save();
    }

    public void unexecute() {
        throw new RuntimeException("Cannot undo");
    }
}