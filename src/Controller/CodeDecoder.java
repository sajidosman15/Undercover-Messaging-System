package Controller;

public class CodeDecoder {

    private char charcode[] = new char[6];

    CodeDecoder(String code) {

        for (int i = 0; i < 6; i++) {
            charcode[i] = code.charAt(i);
        }
        java.util.Arrays.sort(charcode);

    }

    public int NumReturn(char code) {
        int a = 0;

        if (code == charcode[0]) {
            a = 1;
        } else if (code == charcode[1]) {
            a = 2;
        } else if (code == charcode[2]) {
            a = 3;
        } else if (code == charcode[3]) {
            a = 4;
        } else if (code == charcode[4]) {
            a = 5;
        } else {
            a = 6;
        }
        return a;
    }

}
