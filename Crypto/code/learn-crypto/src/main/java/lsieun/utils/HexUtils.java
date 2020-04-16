package lsieun.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

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
        appendHex(sb, values[length - 1]);
        return sb.toString();
    }

    public static byte[] parse(String hex_str, HexFormat format) {
        List<String> list = new ArrayList<>();
        switch (format) {
            case FORMAT_FF_FF: {
                String[] array = hex_str.split(" ");
                list.addAll(Arrays.asList(array));
            }
            break;
            case FORMAT_FFFF: {
                int length = hex_str.length();
                for (int i = 0; i < length; i += 2) {
                    String item = hex_str.substring(i, i + 2);
                    list.add(item);
                }
            }
            break;
            default:
                break;
        }

        int size = list.size();
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; ++i) {
            bytes[i] = (byte) Integer.parseInt(list.get(i), 16);
        }
        return bytes;
    }

    public static String format(byte[] bytes, HexFormat format) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        switch (format) {
            case FORMAT_FF_FF: {
                for (byte b : bytes) {
                    fm.format("%02X ", (b & 0xFF));
                }
            }
            break;
            default:
                break;
        }

        return sb.toString();
    }
}
