package lsieun.bit.c_use;

import lsieun.bit.utils.ByteUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class D_Str_ToUTF8 {
    public static void main(String[] args) {
        byte[] bytes = "戒为良药i".getBytes(StandardCharsets.UTF_8);

        for (byte b : bytes) {
            System.out.println(ByteUtils.toBinary(b));
        }
        System.out.println();

        List<Integer> list = getCodePoints(bytes);
        for (int code_point : list) {
            System.out.printf("%c", code_point);
        }
        System.out.println();
    }

    public static List<Integer> getCodePoints(byte[] bytes) {
        List<Integer> list = new ArrayList<>();

        int index = 0;
        while (index < bytes.length) {
            byte b = bytes[index];
            int count = count_mark(b);

            if (count == 0) {
                list.add(b & 0xFF);
                index++;
                continue;
            }

            int code_point = 0;
            int mask = calculate_mask(count);
            code_point |= (b & mask);
            index++;

            for (int i=0;i<count-1;i++) {
                code_point = code_point << 6 | (bytes[index] & 0x3F);
                index++;
            }
            list.add(code_point);
        }

        return list;
    }

    public static int count_mark(byte b) {
        int count = 0;
        for (int i = 7; i >= 0; i--) {
            int flag = (b & 0xFF) >> i & 0x01;
            if (flag == 0) {
                break;
            }
            count++;
        }
        return count;
    }

    public static int calculate_mask(int count) {
        int mask = 0xFF;
        return mask >> (count + 1);
    }
}
