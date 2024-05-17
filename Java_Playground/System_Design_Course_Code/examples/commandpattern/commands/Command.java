package commands;

import messages.Message;

public abstract class Command {
    protected boolean isReversible = true;
    protected Message message;

    public Command(Message message) {
        this.message = message;
    }

    public abstract void execute();
    public abstract void unexecute();

    public boolean isReversible() {
        return this.isReversible;
    }
}

