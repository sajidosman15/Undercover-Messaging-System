package Entity;

public class Message {

    private int sender;
    private String message;
    private String SendTime;

    public Message(int sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public Message(int sender, String message, String SendTime) {
        this.sender = sender;
        this.message = message;
        this.SendTime = SendTime;
    }

    public String getSendTime() {
        return SendTime;
    }

    public int getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
