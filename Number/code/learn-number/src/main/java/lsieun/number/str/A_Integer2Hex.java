package lsieun.number.str;

/**
 * 这里要学习的点在于：使用{@link Integer#toHexString(int)} 方法输出的字符串长度是不确定的
 */
public class A_Integer2Hex {
    private static final String FORMAT = "|%5s|%8s|%3s|";
    private static final char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void main(String[] args) {
        testHex();
    }

    public static void testHex() {
        String title = String.format(FORMAT, "Byte", "Hex", "Hex");
        System.out.println(title);

        byte[] array = new byte[]{-128, -127, -64, -1, 0, 1, 64, 127};
        for (byte item : array) {
            print(item);
        }
    }

    public static void print(byte b) {
        String line = String.format(FORMAT, b, Integer.toHexString(b), getHexString(b));
        System.out.println(line);
    }

    public static String getHexString(byte b) {
        int hi = (b >> 4) & 0x0f;
        int lo = (b >> 0) & 0x0f;

        StringBuilder sb = new StringBuilder();
        sb.append(chars[hi]);
        sb.append(chars[lo]);
        return sb.toString();
    }
}
