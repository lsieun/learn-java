package lsieun.bit.utils;

public class Format {
    public final int str_len;
    public final int num_len;
    public final String template;

    public Format(int a, int b) {
        int str_len = validStrLength(a, b);
        if (str_len < 3) str_len = 3;

        int num_len = validLength(a, b);
        if (num_len < 4) num_len = 4;

        this.str_len = str_len;
        this.num_len = num_len;
        this.template = "┊ %" + str_len + "s ┊ %" + num_len + "s ┊";
    }

    public static int validLength(int value) {
        int length = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
            int flag = (value >> i) & 0x01;
            if (flag == 1) {
                length = i + 1;
            }
        }
        return length;
    }

    public static int validLength(int a, int b) {
        int a_length = validLength(a);
        int b_length = validLength(b);
        return Math.max(a_length, b_length);
    }

    public static int validStrLength(int a, int b) {
        int a_length = String.valueOf(a).length();
        int b_length = String.valueOf(b).length();
        return Math.max(a_length, b_length);
    }
}
