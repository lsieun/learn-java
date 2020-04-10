package lsieun.utils;

public class HexUtils {
    public static String toHex(int value) {
        byte[] bytes = ByteUtils.toBytes(value);
        return ByteUtils.toHex(bytes);
    }

    private static void appendHex(StringBuilder sb, int value) {
        byte[] bytes = ByteUtils.toBytes(value);
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(Integer.toString((bytes[i] & 0xFF) + 0x100, 16).substring(1));
        }
    }

    public static String toHex(int[] values, String separator) {
        StringBuilder sb = new StringBuilder();

        int length = values.length;
        for (int i = 0; i < length - 1; i++) {
            appendHex(sb, values[i]);
            sb.append(separator);
        }
        appendHex(sb, values[length-1]);
        return sb.toString();
    }
}
