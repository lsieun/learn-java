package lsieun.bit.c_use;

import java.nio.ByteBuffer;
import java.util.Random;

public class C_Port {
    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            byte b1 = (byte) rand.nextInt(256);
            byte b2 = (byte) rand.nextInt(256);

            int result1 = toInt1(b1, b2);
            int result2 = toInt2(b1, b2);

            if (result1 != result2) {
                System.out.println(b1 + ", " + b2 + ": " + result1 + ", " + result2);
            }
        }
    }

    public static int toInt1(byte b1, byte b2) {
        return (b1 & 0xFF) << 8 | (b2 & 0xFF);
    }

    public static int toInt2(byte b1, byte b2) {
        byte[] bytes = new byte[Integer.BYTES];
        bytes[0] = 0;
        bytes[1] = 0;
        bytes[2] = b1;
        bytes[3] = b2;

        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        return byteBuffer.getInt();
    }
}
