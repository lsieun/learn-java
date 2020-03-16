package lsieun.crypto.des;

import static lsieun.crypto.des.DESConst.sbox;

public class DESUtils {
    /**
     * This does not return a 1 for a 1 bit; it just returns non-zero
     */
    public static int get_bit(byte[] array, int bit) {
        return (array[bit / 8] & 0xFF) & (0x80 >> (bit % 8));
    }

    public static void set_bit(byte[] array, int bit) {
        int val = (array[bit / 8] & 0xFF) | (0x80 >> (bit % 8));
        array[bit / 8] = (byte) val;
    }

    public static void clear_bit(byte[] array, int bit) {
        int val = (array[bit / 8] & 0xFF) & ~(0x80 >> (bit % 8));
        array[bit / 8] = (byte) val;
    }

    //TODO: 其实，可以重复利用已有的byte[]，而不需要重新分配新的byte[]
    public static byte[] xor(byte[] first_bytes, byte[] second_bytes, int size) {
        byte[] result_bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            result_bytes[i] = (byte) (first_bytes[i] ^ second_bytes[i]);
        }
        return result_bytes;
    }

    /**
     * <p>Implement the permutation functions.</p>
     * NOTE: this assumes that the permutation tables are defined as one-based
     * rather than 0-based arrays, since they’re given that way in the
     * specification.
     */
    public static byte[] permute(byte[] src, int[] permute_table) {
        int bit_size = permute_table.length;
        int byte_size = bit_size / 8;
        byte[] target = new byte[byte_size];

        for (int i = 0; i < bit_size; i++) {
            int pos = permute_table[i] - 1;

            if (get_bit(src, pos) == 0) {
                clear_bit(target, i);
            } else {
                set_bit(target, i);
            }
        }
        return target;
    }

    @SuppressWarnings("Duplicates")
    public static void rotate_left(byte[] key_56_bit_bytes) {
        int val0 = key_56_bit_bytes[0] & 0xFF;
        int val1 = key_56_bit_bytes[1] & 0xFF;
        int val2 = key_56_bit_bytes[2] & 0xFF;
        int val3 = key_56_bit_bytes[3] & 0xFF;
        int val4 = key_56_bit_bytes[4] & 0xFF;
        int val5 = key_56_bit_bytes[5] & 0xFF;
        int val6 = key_56_bit_bytes[6] & 0xFF;

        int carry_left = (val0 & 0x80) >> 3;
        val0 = (val0 << 1) | ((val1 & 0x80) >> 7);
        val1 = (val1 << 1) | ((val2 & 0x80) >> 7);
        val2 = (val2 << 1) | ((val3 & 0x80) >> 7);

        // special handling for byte 3
        int carry_right = (val3 & 0x08) >> 3;
        val3 = (((val3 << 1) | ((val4 & 0x80) >> 7)) & ~0x10) | carry_left;

        val4 = (val4 << 1) | ((val5 & 0x80) >> 7);
        val5 = (val5 << 1) | ((val6 & 0x80) >> 7);
        val6 = (val6 << 1) | carry_right;

        key_56_bit_bytes[0] = (byte) val0;
        key_56_bit_bytes[1] = (byte) val1;
        key_56_bit_bytes[2] = (byte) val2;
        key_56_bit_bytes[3] = (byte) val3;
        key_56_bit_bytes[4] = (byte) val4;
        key_56_bit_bytes[5] = (byte) val5;
        key_56_bit_bytes[6] = (byte) val6;
    }

    @SuppressWarnings("Duplicates")
    public static void rotate_left_twice(byte[] key_56_bit_bytes) {
        int val0 = key_56_bit_bytes[0] & 0xFF;
        int val1 = key_56_bit_bytes[1] & 0xFF;
        int val2 = key_56_bit_bytes[2] & 0xFF;
        int val3 = key_56_bit_bytes[3] & 0xFF;
        int val4 = key_56_bit_bytes[4] & 0xFF;
        int val5 = key_56_bit_bytes[5] & 0xFF;
        int val6 = key_56_bit_bytes[6] & 0xFF;

        int carry_left = (val0 & 0xC0) >> 2;
        val0 = (val0 << 2) | ((val1 & 0xC0) >> 6);
        val1 = (val1 << 2) | ((val2 & 0xC0) >> 6);
        val2 = (val2 << 2) | ((val3 & 0xC0) >> 6);

        // special handling for byte 3
        int carry_right = (val3 & 0x0C) >> 2;
        val3 = (((val3 << 2) | ((val4 & 0xC0) >> 6)) & ~0x30) | carry_left;

        val4 = (val4 << 2) | ((val5 & 0xC0) >> 6);
        val5 = (val5 << 2) | ((val6 & 0xC0) >> 6);
        val6 = (val6 << 2) | carry_right;

        key_56_bit_bytes[0] = (byte) val0;
        key_56_bit_bytes[1] = (byte) val1;
        key_56_bit_bytes[2] = (byte) val2;
        key_56_bit_bytes[3] = (byte) val3;
        key_56_bit_bytes[4] = (byte) val4;
        key_56_bit_bytes[5] = (byte) val5;
        key_56_bit_bytes[6] = (byte) val6;
    }

    @SuppressWarnings("Duplicates")
    public static void rotate_right(byte[] key_56_bit_bytes) {
        int val0 = key_56_bit_bytes[0] & 0xFF;
        int val1 = key_56_bit_bytes[1] & 0xFF;
        int val2 = key_56_bit_bytes[2] & 0xFF;
        int val3 = key_56_bit_bytes[3] & 0xFF;
        int val4 = key_56_bit_bytes[4] & 0xFF;
        int val5 = key_56_bit_bytes[5] & 0xFF;
        int val6 = key_56_bit_bytes[6] & 0xFF;

        int carry_left;
        int carry_right;

        carry_right = (val6 & 0x01) << 3;
        val6 = (val6 >> 1) | ((val5 & 0x01) << 7);
        val5 = (val5 >> 1) | ((val4 & 0x01) << 7);
        val4 = (val4 >> 1) | ((val3 & 0x01) << 7);

        carry_left = (val3 & 0x10) << 3;
        val3 = (((val3 >> 1) | ((val2 & 0x01) << 7)) & ~0x08) | carry_right;
        val2 = (val2 >> 1) | ((val1 & 0x01) << 7);
        val1 = (val1 >> 1) | ((val0 & 0x01) << 7);
        val0 = (val0 >> 1) | carry_left;

        key_56_bit_bytes[0] = (byte) val0;
        key_56_bit_bytes[1] = (byte) val1;
        key_56_bit_bytes[2] = (byte) val2;
        key_56_bit_bytes[3] = (byte) val3;
        key_56_bit_bytes[4] = (byte) val4;
        key_56_bit_bytes[5] = (byte) val5;
        key_56_bit_bytes[6] = (byte) val6;
    }

    @SuppressWarnings("Duplicates")
    public static void rotate_right_twice(byte[] key_56_bit_bytes) {
        int val0 = key_56_bit_bytes[0] & 0xFF;
        int val1 = key_56_bit_bytes[1] & 0xFF;
        int val2 = key_56_bit_bytes[2] & 0xFF;
        int val3 = key_56_bit_bytes[3] & 0xFF;
        int val4 = key_56_bit_bytes[4] & 0xFF;
        int val5 = key_56_bit_bytes[5] & 0xFF;
        int val6 = key_56_bit_bytes[6] & 0xFF;

        int carry_left;
        int carry_right;

        carry_right = (val6 & 0x03) << 2;
        val6 = (val6 >> 2) | ((val5 & 0x03) << 6);
        val5 = (val5 >> 2) | ((val4 & 0x03) << 6);
        val4 = (val4 >> 2) | ((val3 & 0x03) << 6);

        carry_left = (val3 & 0x30) << 2;
        val3 = (((val3 >> 2) | ((val2 & 0x03) << 6)) & ~0x0C) | carry_right;
        val2 = (val2 >> 2) | ((val1 & 0x03) << 6);
        val1 = (val1 >> 2) | ((val0 & 0x03) << 6);
        val0 = (val0 >> 2) | carry_left;

        key_56_bit_bytes[0] = (byte) val0;
        key_56_bit_bytes[1] = (byte) val1;
        key_56_bit_bytes[2] = (byte) val2;
        key_56_bit_bytes[3] = (byte) val3;
        key_56_bit_bytes[4] = (byte) val4;
        key_56_bit_bytes[5] = (byte) val5;
        key_56_bit_bytes[6] = (byte) val6;
    }

    public static byte[] get_substitution(byte[] bytes) {
        int[] substitution_blocks = new int[4];
        substitution_blocks[0] = sbox[0][(bytes[0] & 0xFC) >> 2] << 4;
        substitution_blocks[0] |= sbox[1][(bytes[0] & 0x03) << 4 | (bytes[1] & 0xF0) >> 4];
        substitution_blocks[1] = sbox[2][(bytes[1] & 0x0F) << 2 | (bytes[2] & 0xC0) >> 6] << 4;
        substitution_blocks[1] |= sbox[3][(bytes[2] & 0x3F)];
        substitution_blocks[2] = sbox[4][(bytes[3] & 0xFC) >> 2] << 4;
        substitution_blocks[2] |= sbox[5][(bytes[3] & 0x03) << 4 | (bytes[4] & 0xF0) >> 4];
        substitution_blocks[3] = sbox[6][(bytes[4] & 0x0F) << 2 | (bytes[5] & 0xC0) >> 6] << 4;
        substitution_blocks[3] |= sbox[7][(bytes[5] & 0x3F)];

        byte[] substitution_bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            substitution_bytes[i] = (byte) substitution_blocks[i];
        }
        return substitution_bytes;
    }

}
