package lsieun.crypto.sym.des;

//http://www.herongyang.com/Cryptography/DES-Illustration-DES-Key-Schedule.html
public class DESSubkeysTest {
    public static void main(String[] a) {
        try {
            byte[] theKey = getTestKey();
            byte[][] subKeys = getSubkeys(theKey);
            boolean ok = validateSubkeys(subKeys);
            System.out.println("DES subkeys test result: " + ok);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static final int[] PC1 = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };
    static final int[] PC2 = {
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };
    static final int[] SHIFTS = {
            1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };

    public static byte[][] getSubkeys(byte[] theKey) {
        printBytes(theKey, "Input key");
        int activeKeySize = PC1.length;
        int numOfSubKeys = SHIFTS.length;
        byte[] activeKey = selectBits(theKey, PC1);
        printBytes(activeKey, "After permuted choice 1 - Active key");
        int halfKeySize = activeKeySize / 2;
        byte[] c = selectBits(activeKey, 0, halfKeySize);
        byte[] d = selectBits(activeKey, halfKeySize, halfKeySize);
        byte[][] subKeys = new byte[numOfSubKeys][];
        for (int k = 0; k < numOfSubKeys; k++) {
            c = rotateLeft(c, halfKeySize, SHIFTS[k]);
            d = rotateLeft(d, halfKeySize, SHIFTS[k]);
            byte[] cd = concatenateBits(c, halfKeySize, d, halfKeySize);
            printBytes(cd, "Subkey #" + (k + 1) + " after shifting");
            subKeys[k] = selectBits(cd, PC2);
            printBytes(subKeys[k], "Subkey #" + (k + 1)
                    + " after permuted choice 2");
        }
        return subKeys;
    }

    private static byte[] rotateLeft(byte[] in, int len, int step) {
        int numOfBytes = (len - 1) / 8 + 1;
        byte[] out = new byte[numOfBytes];
        for (int i = 0; i < len; i++) {
            int val = getBit(in, (i + step) % len);
            setBit(out, i, val);
        }
        return out;
    }

    private static byte[] concatenateBits(byte[] a, int aLen, byte[] b,
                                          int bLen) {
        int numOfBytes = (aLen + bLen - 1) / 8 + 1;
        byte[] out = new byte[numOfBytes];
        int j = 0;
        for (int i = 0; i < aLen; i++) {
            int val = getBit(a, i);
            setBit(out, j, val);
            j++;
        }
        for (int i = 0; i < bLen; i++) {
            int val = getBit(b, i);
            setBit(out, j, val);
            j++;
        }
        return out;
    }

    private static byte[] selectBits(byte[] in, int pos, int len) {
        int numOfBytes = (len - 1) / 8 + 1;
        byte[] out = new byte[numOfBytes];
        for (int i = 0; i < len; i++) {
            int val = getBit(in, pos + i);
            setBit(out, i, val);
        }
        return out;
    }

    private static byte[] selectBits(byte[] in, int[] map) {
        int numOfBytes = (map.length - 1) / 8 + 1;
        byte[] out = new byte[numOfBytes];
        for (int i = 0; i < map.length; i++) {
            int val = getBit(in, map[i] - 1);
            setBit(out, i, val);
//       System.out.println("i="+i+", pos="+(map[i]-1)+", val="+val);
        }
        return out;
    }

    private static int getBit(byte[] data, int pos) {
        int posByte = pos / 8;
        int posBit = pos % 8;
        byte valByte = data[posByte];
        int valInt = valByte >> (8 - (posBit + 1)) & 0x0001;
        return valInt;
    }

    private static void setBit(byte[] data, int pos, int val) {
        int posByte = pos / 8;
        int posBit = pos % 8;
        byte oldByte = data[posByte];
        oldByte = (byte) (((0xFF7F >> posBit) & oldByte) & 0x00FF);
        byte newByte = (byte) ((val << (8 - (posBit + 1))) | oldByte);
        data[posByte] = newByte;
    }

    private static void printBytes(byte[] data, String name) {
        System.out.println("");
        System.out.println(name + ":");
        for (int i = 0; i < data.length; i++) {
            System.out.print(byteToBits(data[i]) + " ");
        }
        System.out.println();
    }

    private static String byteToBits(byte b) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 8; i++)
            buf.append((int) (b >> (8 - (i + 1)) & 0x0001));
        return buf.toString();
    }

    public static byte[] getTestKey() {
        String strKey = " 00010011 00110100 01010111 01111001"
                + " 10011011 10111100 11011111 11110001";
        byte[] theKey = new byte[8];
        for (int i = 0; i < 8; i++) {
            String strByte = strKey.substring(9 * i + 1, 9 * i + 1 + 8);
            theKey[i] = (byte) Integer.parseInt(strByte, 2);
        }
        return theKey;
    }

    private static boolean validateSubkeys(byte[][] subKeys) {
        boolean ok = true;
        String[] strKeys = {
                " 00011011 00000010 11101111 11111100 01110000 01110010",//1
                " 01111001 10101110 11011001 11011011 11001001 11100101",//2
                " 01010101 11111100 10001010 01000010 11001111 10011001",//3
                " 01110010 10101101 11010110 11011011 00110101 00011101",//4
                " 01111100 11101100 00000111 11101011 01010011 10101000",//5
                " 01100011 10100101 00111110 01010000 01111011 00101111",//6
                " 11101100 10000100 10110111 11110110 00011000 10111100",//7
                " 11110111 10001010 00111010 11000001 00111011 11111011",//8
                " 11100000 11011011 11101011 11101101 11100111 10000001",//9
                " 10110001 11110011 01000111 10111010 01000110 01001111",//0
                " 00100001 01011111 11010011 11011110 11010011 10000110",//1
                " 01110101 01110001 11110101 10010100 01100111 11101001",//2
                " 10010111 11000101 11010001 11111010 10111010 01000001",//3
                " 01011111 01000011 10110111 11110010 11100111 00111010",//4
                " 10111111 10010001 10001101 00111101 00111111 00001010",//5
                " 11001011 00111101 10001011 00001110 00010111 11110101"};
        for (int k = 0; k < 16; k++) {
            for (int i = 0; i < 6; i++) {
                String strByte = strKeys[k].substring(9 * i + 1, 9 * i + 1 + 8);
                byte keyByte = (byte) Integer.parseInt(strByte, 2);
                if (keyByte != subKeys[k][i]) ok = false;
            }
        }
        return ok;
    }
}
