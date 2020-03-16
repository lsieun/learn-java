package lsieun.crypto.des;

import java.util.Objects;

public class DES {
    private static final int DES_BLOCK_SIZE = 8;

    public static byte[] des_encrypt(byte[] plain_text_bytes, byte[] key_64_bit_bytes) {
        int plain_text_len = plain_text_bytes.length;
        int padding_len = DES_BLOCK_SIZE - (plain_text_len % DES_BLOCK_SIZE);
        byte[] padded_plain_text_bytes = new byte[plain_text_len + padding_len];

        // This implements NIST 800-3A padding
        System.arraycopy(plain_text_bytes, 0, padded_plain_text_bytes, 0, plain_text_len);
        padded_plain_text_bytes[plain_text_len] = (byte) 0x80;

        // encrypt
        byte[] encrypted_bytes = des_operate(padded_plain_text_bytes, key_64_bit_bytes, CipherType.ENCRYPT);
        return encrypted_bytes;
    }

    public static byte[] des_encrypt_pkcs5(byte[] plain_text_bytes, byte[] key_64_bit_bytes) {
        int plain_text_len = plain_text_bytes.length;
        int padding_len = DES_BLOCK_SIZE - (plain_text_len % DES_BLOCK_SIZE);
        byte[] padded_plain_text_bytes = new byte[plain_text_len + padding_len];

        // This implements PKCS #5 padding.
        System.arraycopy(plain_text_bytes, 0, padded_plain_text_bytes, 0, plain_text_len);
        for (int i = 0; i < padding_len; i++) {
            padded_plain_text_bytes[plain_text_len + i] = (byte) padding_len;
        }

        // encrypt
        byte[] encrypted_bytes = des_operate(padded_plain_text_bytes, key_64_bit_bytes, CipherType.ENCRYPT);
        return encrypted_bytes;
    }

    @SuppressWarnings("Duplicates")
    public static byte[] des_operate(byte[] input, byte[] key_64_bit_bytes, CipherType type) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(key_64_bit_bytes);

        int input_length = input.length;
        if (input_length % 8 != 0) {
            throw new IllegalArgumentException("input's length is not valid");
        }

        byte[] output = new byte[input_length];
        byte[] input_block = new byte[8];
        int times = input_length / 8;
        for (int i = 0; i < times; i++) {
            System.arraycopy(input_block, 0, input, i * 8, 8);
            byte[] encrypted_bytes = des_block_operate(input_block, key_64_bit_bytes, type);
            System.arraycopy(encrypted_bytes, 0, output, i * 8, 8);
        }
        return output;
    }


    public static byte[] des_block_operate(byte[] plain_text_64_bit_bytes, byte[] key_64_bit_bytes, CipherType type) {
        // Initial permutation
        byte[] content_64_bit_bytes = DESUtils.permute(plain_text_64_bit_bytes, DESConst.ip_table);

        // Key schedule computation
        byte[] key_56_bit_bytes = DESUtils.permute(key_64_bit_bytes, DESConst.pc1_table);

        // (1) 16 rounds
        byte[] current_56_bit_key_bytes = new byte[7];
        System.arraycopy(key_56_bit_bytes, 0, current_56_bit_key_bytes, 0, 7);

        for (int i = 1; i <= 16; i++) {

            // key
            if (type == CipherType.ENCRYPT) {
                if (i == 1 || i == 2 || i == 9 || i == 16) {
                    DESUtils.rotate_left(current_56_bit_key_bytes);
                } else {
                    DESUtils.rotate_left_twice(current_56_bit_key_bytes);
                }
            }
            byte[] current_48_bit_sub_key_bytes = DESUtils.permute(current_56_bit_key_bytes, DESConst.pc2_table);
            if (type == CipherType.DECRYPT) {
                if (i == 16 || i == 15 || i == 8 || i == 1) {
                    DESUtils.rotate_right(current_56_bit_key_bytes);
                } else {
                    DESUtils.rotate_right_twice(current_56_bit_key_bytes);
                }
            }

            // msg
            byte[] left_32_bit_bytes = new byte[4];
            byte[] right_32_bit_bytes = new byte[4];
            System.arraycopy(content_64_bit_bytes, 0, left_32_bit_bytes, 0, 4);
            System.arraycopy(content_64_bit_bytes, 4, right_32_bit_bytes, 0, 4);


            byte[] expansion_48_bit_bytes = DESUtils.permute(right_32_bit_bytes, DESConst.expansion_table);
            byte[] xor_48_bit_bytes = DESUtils.xor(expansion_48_bit_bytes, current_48_bit_sub_key_bytes, 6);
            byte[] substitution_32_bit_bytes = DESUtils.get_substitution(xor_48_bit_bytes);
            byte[] permutation_32_bit_bytes = DESUtils.permute(substitution_32_bit_bytes, DESConst.p_table);
            byte[] xor_32_bit_bytes = DESUtils.xor(permutation_32_bit_bytes, left_32_bit_bytes, 4);

            // copy
            System.arraycopy(right_32_bit_bytes, 0, content_64_bit_bytes, 0, 4);
            System.arraycopy(xor_32_bit_bytes, 0, content_64_bit_bytes, 4, 4);
        }


        // (2) Swap one last time
        byte[] swap_bytes = new byte[8];
        System.arraycopy(content_64_bit_bytes, 4, swap_bytes, 0, 4);
        System.arraycopy(content_64_bit_bytes, 0, swap_bytes, 4, 4);

        // (3) Final permutation (undo initial permutation)
        byte[] encrypted_64_bit_bytes = DESUtils.permute(swap_bytes, DESConst.fp_table);

        return encrypted_64_bit_bytes;
    }
}
