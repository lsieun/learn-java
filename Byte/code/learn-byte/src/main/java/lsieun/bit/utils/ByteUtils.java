package lsieun.bit.utils;

public class ByteUtils {
    private static final char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static int calculate(int a, int b, BitOp op) {
        switch (op) {
            case AND:
                return a & b;
            case EXCLUSIVE_OR:
                return a ^ b;
            case INCLUSIVE_OR:
                return a | b;
            default:
                throw new IllegalArgumentException("Operator is not Valid: " + op.val);
        }
    }

    public static int calculate(int a, BitOp op) {
        switch (op) {
            case COMPLEMENT:
                return ~a;
            default:
                throw new IllegalArgumentException("Operator is not Valid: " + op.val);
        }
    }

    public static String toBinary(byte b) {
        return toBinary(b,true);
    }

    public static String toBinary(byte b, boolean separate) {
        StringBuilder sb = new StringBuilder();
        sb.append((b >> 7) & 0x01);
        sb.append((b >> 6) & 0x01);
        sb.append((b >> 5) & 0x01);
        sb.append((b >> 4) & 0x01);
        if (separate) {
            sb.append(" ");
        }
        sb.append((b >> 3) & 0x01);
        sb.append((b >> 2) & 0x01);
        sb.append((b >> 1) & 0x01);
        sb.append((b >> 0) & 0x01);
        return sb.toString();
    }

    public static String toBinary(int num) {
        return toBinary(num, Integer.SIZE);
    }

    public static String toBinary(int num, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            sb.append((num >> i) & 0x01);
        }
        return sb.toString();
    }

    public static String toHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            sb.append(chars[(b & 0xFF) >> 4]);
            sb.append(chars[b & 0x0F]);
        }
        return sb.toString();
    }

}
