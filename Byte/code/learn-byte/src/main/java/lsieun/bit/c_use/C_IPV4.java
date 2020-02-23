package lsieun.bit.c_use;

import lsieun.bit.utils.ByteUtils;

import java.nio.ByteBuffer;
import java.util.Random;

public class C_IPV4 {
    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int value = rand.nextInt();
            byte[] bytes = getBytes(value);
            String ip = toIP4(value);

            System.out.println(value);
            System.out.println(ByteUtils.toBinary(value, 32));
            for (byte b : bytes) {
                System.out.println(ByteUtils.toBinary(b, false));
            }
            System.out.println(ip);
            System.out.println();
        }
    }

    public static byte[] getBytes(int value) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
        byteBuffer.putInt(value);
        byteBuffer.flip();
        return byteBuffer.array();
    }

    public static String toIP4(int value) {
        int i1 = value >> 24 & 0xFF;
        int i2 = value >> 16 & 0xFF;
        int i3 = value >> 8 & 0xFF;
        int i4 = value & 0xFF;
        return String.format("%d.%d.%d.%d", i1, i2, i3, i4);
    }
}
