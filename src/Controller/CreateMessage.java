package Controller;

import java.util.Random;

public class CreateMessage {

    private char charmessage[];
    private Random ran = new Random();
    private int num, size, charcode;
    private int reminder1, reminder2, reminder3;
    private char numcode;

    private String maskMessage(String message) {
        charmessage = message.toCharArray();
        CodeGenerator generator = new CodeGenerator();
        message = generator.createcode();
        size = charmessage.length;

        for (int i = 0; i < size; i++) {
            num = ran.nextInt(6) + 1;
            numcode = generator.ReturnCode(num);
            message = message + numcode;
            charcode = (int) charmessage[i];
            charcode = charcode - num;
            message = message + (char) charcode;
        }
        return message;

    }

    protected String getMessage(String message) {
        message = maskMessage(message);

        charmessage = message.toCharArray();
        message = "";
        size = charmessage.length;

        for (int i = 0; i < size; i++) {
            charcode = (int) charmessage[i];
            reminder1 = charcode % 2;
            charcode = charcode / 2;
            reminder2 = charcode % 2;
            charcode = charcode / 2;
            reminder3 = charcode % 2;
            charcode = charcode / 2;

            if (reminder1 == 0 && reminder2 == 0 && reminder3 == 0) {
                message = message + "0";
            } else if (reminder1 == 1 && reminder2 == 0 && reminder3 == 0) {
                message = message + "1";
            } else if (reminder1 == 0 && reminder2 == 1 && reminder3 == 0) {
                message = message + "2";
            } else if (reminder1 == 1 && reminder2 == 1 && reminder3 == 0) {
                message = message + "3";
            } else if (reminder1 == 0 && reminder2 == 0 && reminder3 == 1) {
                message = message + "4";
            } else if (reminder1 == 1 && reminder2 == 0 && reminder3 == 1) {
                message = message + "5";
            } else if (reminder1 == 0 && reminder2 == 1 && reminder3 == 1) {
                message = message + "6";
            } else {
                message = message + "7";
            }

            if (charcode < 10) {
                message = message + "0";
                message = message + charcode;
            } else {
                message = message + charcode;
            }

        }
        return message;
    }
}
