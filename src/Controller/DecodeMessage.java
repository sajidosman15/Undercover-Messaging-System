package Controller;

public class DecodeMessage {

    private char charmessage[];
    private int size, reminder, code;
    private String data;

    public String getMessage(String message) {
        charmessage = message.toCharArray();
        message = "";
        size = charmessage.length;
        for (int i = 0; i < size; i = i + 3) {
            data = "" + charmessage[i];
            reminder = Integer.parseInt(data);
            data = "" + charmessage[i + 1] + charmessage[i + 2];
            code = Integer.parseInt(data);
            for (int j = 0; j < 3; j++) {
                code = code * 2;
            }
            code = code + reminder;
            message = message + (char) code;
        }
        message = returnMessage(message);
        return message;

    }

    private String returnMessage(String message) {
        int num, datacode;
        char schar;
        charmessage = message.toCharArray();
        message = "";
        data = "";
        size = charmessage.length;

        for (int i = 0; i < 6; i++) {
            data = data + charmessage[i];
        }

        CodeDecoder decoder = new CodeDecoder(data);

        for (int i = 6; i < size - 1; i = i + 2) {
            num = decoder.NumReturn(charmessage[i]);
            datacode = (int) charmessage[i + 1] + num;
            schar = (char) datacode;
            message = message + schar;
        }
        return message;
    }
}
