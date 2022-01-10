package Controller;

import java.util.Random;

public class CodeGenerator {

    private char data[] = new char[6];
    private int a;
    private Random ran;

    public CodeGenerator() {
        ran = new Random();
        a = ran.nextInt(9) + 48;
        data[0] = (char) a;
        a = ran.nextInt(7) + 65;
        data[1] = (char) a;
        a = ran.nextInt(7) + 74;
        data[2] = (char) a;
        a = ran.nextInt(6) + 83;
        data[3] = (char) a;
        a = ran.nextInt(9) + 97;
        data[4] = (char) a;
        a = ran.nextInt(9) + 109;
        data[5] = (char) a;
    }

    public String createcode() {
        ran = new Random();
        a = ran.nextInt(20) + 1;
        String code = "";
        char swap;
        char data1[] = new char[6];

        System.arraycopy(data, 0, data1, 0, 6);

        for (int i = 0; i < a; i++) {
            swap = data1[0];
            data1[0] = data1[3];
            data1[3] = swap;
            swap = data1[1];
            data1[1] = data1[4];
            data1[4] = swap;
            swap = data1[2];
            data1[2] = data1[5];
            data1[5] = swap;
            swap = data1[5];
            data1[5] = data1[0];
            data1[0] = swap;
        }

        for (int i = 0; i < 6; i++) {
            code = code + data1[i];
        }
        return code;
    }

    public char ReturnCode(int number) {
        char code;
        switch (number) {
            case 1:
                code = data[0];
                break;
            case 2:
                code = data[1];
                break;
            case 3:
                code = data[2];
                break;
            case 4:
                code = data[3];
                break;
            case 5:
                code = data[4];
                break;
            default:
                code = data[5];
                break;
        }
        return code;
    }

}
