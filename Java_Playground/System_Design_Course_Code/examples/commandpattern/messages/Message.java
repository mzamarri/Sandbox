package messages;

public class Message {
    private static String message = "";
    private String tempMessage;
    private int currentPosition;

    public Message(String text) {
        this.tempMessage = text;
        this.currentPosition = tempMessage.length();
    }

    public void movePosition(int position) {
        this.currentPosition = position;
    }

    public void delete(int num) {
        StringBuilder sb = new StringBuilder(this.tempMessage);
        sb.delete(this.currentPosition - num, this.currentPosition-1);
        this.tempMessage = sb.toString();
        this.currentPosition -= num;
        System.out.println("Deleted");
    }

    public void paste(String text) {
        StringBuilder sb = new StringBuilder(this.tempMessage);
        sb.insert(this.currentPosition, text);
        this.tempMessage = sb.toString();
        this.currentPosition += text.length();
        System.out.println("Pasted");
    }

    public void save() {
        Message.message = this.tempMessage;
        System.out.println("Saved");
    }

    public void resetMessage() {
        tempMessage = "";
    }

    public String getTempMessage() {
        return this.tempMessage;
    }

    public void printMessage() {
        System.out.println("Message: " + Message.message);
    }
}