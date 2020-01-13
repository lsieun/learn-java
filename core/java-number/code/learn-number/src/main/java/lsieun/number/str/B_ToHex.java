package lsieun.number.str;

public class B_ToHex {
    private static final String FORMAT = "|%5s|%3s|";
    private static final char[] chars = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void main(String[] args) {
        testHex();
    }

    public static void testHex() {
        String title = String.format(FORMAT, "Byte", "Hex");
        System.out.println(title);

        byte[] array = new byte[]{-128, -127, -64, -1, 0, 1, 64, 127};
        for (byte item : array) {
            print(item);
        }
    }

    public static void print(byte b) {
        String line = String.format(FORMAT, b, getHexString(b));
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
