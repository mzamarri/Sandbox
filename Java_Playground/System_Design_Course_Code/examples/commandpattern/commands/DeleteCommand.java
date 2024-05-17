package commands;

import messages.Message;

public class DeleteCommand extends Command {
    private int textLength;
    private String deletedText;

    public DeleteCommand(Message message, int textLength) {
        super(message);
        this.textLength = textLength;
    }

    public void execute() {
        System.out.println("Deleting...");
        String tempMessage = message.getTempMessage();
        this.deletedText = tempMessage.substring(tempMessage.length() - textLength);
        message.delete(this.textLength);
        System.out.println("tempMessage: " + message.getTempMessage());
    }

    public void unexecute() {
        message.paste(this.deletedText);
    }
}