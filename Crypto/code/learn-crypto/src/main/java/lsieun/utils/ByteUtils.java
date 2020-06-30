package lsieun.utils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

public class ByteUtils {

    public static byte[] fromInt(int x) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(x);
        return buffer.array();
    }

    public static byte[] fromLong(long num) {
        int size = Long.BYTES;
        byte[] bytes = new byte[size];

        for (int i = 0; i < size; i++) {
            bytes[i] = (byte) ((num >> ((size - 1 - i) * 8)) & 0xFF);
        }

        return bytes;
    }

    public static int toInt(byte[] bytes) {
        int length = bytes.length;

        int sum = 0;
        for (int i = 0; i < length; i++) {
            int index = length - 1 - i;
            int val = bytes[index] & 0xFF;
            sum += (val << (i * 8));
        }
        return sum;
    }

    public static boolean is_loop(List<byte[]> list) {
        int size = list.size();
        if (size % 2 != 0) return false;
        int half = size / 2;
        for (int i = 0; i < half; i++) {
            byte[] bytes1 = list.get(i);
            byte[] bytes2 = list.get(i + half);
            if (!Arrays.equals(bytes1, bytes2)) {
                return false;
            }
        }
        return true;
    }

    public static byte[] xor(byte[] bytes1, byte[] bytes2, int num) {
        byte[] result_bytes = new byte[num];
        for (int i = 0; i < num; i++) {
            result_bytes[i] = (byte) (bytes1[i] ^ bytes2[i]);
        }
        return result_bytes;
    }

    public static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(Integer.toString((bytes[i] & 0xFF) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String toBinary(byte[] bytes) {
        if (bytes == null) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            toBinary(sb, b);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String toBinary(byte b) {
        StringBuilder sb = new StringBuilder();
        toBinary(sb, b);
        return sb.toString();
    }

    private static void toBinary(StringBuilder sb, byte b) {
        for (int i = 7; i >= 0; i--) {
            int val = (b >> i) & 0x01;
            sb.append("" + val);
        }
    }

    public static byte[] toBytes(int value) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(value);
        byte[] array = buffer.array();
        return array;
    }

    public static List<byte[]> toList(byte[] bytes, int block_size) {
        int length = bytes.length;
        int count = length / block_size;

        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            byte[] block_bytes = new byte[block_size];
            System.arraycopy(bytes, i * block_size, block_bytes, 0, block_size);
            list.add(block_bytes);
        }
        return list;
    }

    public static byte[] fromHex(String hex_str, String separator) {
        String[] array = hex_str.split(separator);
        int length = array.length;

        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            String item = array[i];
            int value = Integer.parseInt(item, 16);
            bytes[i] = (byte) value;
        }
        return bytes;
    }

    public static byte[] fromHex(String hex_str) {
        int length = hex_str.length();
        int count = length / 2;

        byte[] bytes = new byte[count];
        for (int i = 0; i < count; i++) {
            String item = hex_str.substring(2 * i, 2 * i + 2);
            int value = Integer.parseInt(item, 16);
            bytes[i] = (byte) value;
        }
        return bytes;
    }

    public static String toProgramCode(String hex_str) {
        byte[] bytes = fromHex(hex_str);
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        for (int i = 0; i < bytes.length; ++i) {
            fm.format("0x%s, ", Integer.toString((bytes[i] & 0xFF) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static byte[] concatenate(byte[] bytes1, byte[] bytes2) {
        int len1 = bytes1.length;
        int len2 = bytes2.length;

        byte[] result_bytes = new byte[len1 + len2];

        System.arraycopy(bytes1, 0, result_bytes, 0, len1);
        System.arraycopy(bytes2, 0, result_bytes, len1, len2);

        return result_bytes;
    }

    public static void main(String[] args) {
        String hex_str = "2B57C023 5FB74897 68D058FF 4911C20F DBE71E36 99D91339 AFBB903E E17255DC";
        String result = toProgramCode(hex_str.replaceAll(" ", ""));
        System.out.println(result.toUpperCase());
    }
}
