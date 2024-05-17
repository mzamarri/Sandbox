package commands;

import messages.Message;

public class PasteCommand extends Command {
    private String text;
    
    public PasteCommand(Message message, String text) {
        super(message);
        this.text = text;
    }

    public void execute() {
        System.out.println("Pasting...");
        message.paste(text);
        System.out.println("tempMessage: " + message.getTempMessage());
    }

    public void unexecute() {

    }
}