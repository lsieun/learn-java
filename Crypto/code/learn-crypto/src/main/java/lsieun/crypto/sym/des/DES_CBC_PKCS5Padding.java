package lsieun.crypto.sym.des;

import java.util.Objects;

public class DES_CBC_PKCS5Padding {
    public static final int DES_BLOCK_SIZE = 8;

    @SuppressWarnings("Duplicates")
    public static byte[] des_encrypt(byte[] plain_text_bytes, byte[] key_64_bit_bytes, byte[] iv_64_bit_bytes) {
        // calculate padding length
        int plain_text_len = plain_text_bytes.length;
        int padding_len = DES_BLOCK_SIZE - (plain_text_len % DES_BLOCK_SIZE);
        byte[] padded_plain_text_bytes = new byte[plain_text_len + padding_len];

        // This implements PKCS #5 padding.
        System.arraycopy(plain_text_bytes, 0, padded_plain_text_bytes, 0, plain_text_len);
        for (int i = 0; i < padding_len; i++) {
            padded_plain_text_bytes[plain_text_len + i] = (byte) padding_len;
        }

        // encrypt
        byte[] encrypted_bytes = des_operate(padded_plain_text_bytes, key_64_bit_bytes, iv_64_bit_bytes, CipherType.ENCRYPT);
        return encrypted_bytes;
    }

    @SuppressWarnings("Duplicates")
    public static byte[] des_decrypt(byte[] cipher_text_64_bit_bytes, byte[] key_64_bit_bytes, byte[] iv_64_bit_bytes) {
        // decrypt
        byte[] padded_plain_text_bytes = des_operate(cipher_text_64_bit_bytes, key_64_bit_bytes, iv_64_bit_bytes, CipherType.DECRYPT);

        // calculate padding length
        int padded_plain_text_len = padded_plain_text_bytes.length;
        int padded_len = padded_plain_text_bytes[padded_plain_text_len - 1];
        int plain_text_len = padded_plain_text_len - padded_len;

        // remove PKCS #5 padding.
        byte[] plain_text_bytes = new byte[plain_text_len];
        System.arraycopy(padded_plain_text_bytes, 0, plain_text_bytes, 0, plain_text_len);
        return plain_text_bytes;
    }

    @SuppressWarnings("Duplicates")
    public static byte[] des_operate(byte[] input, byte[] key_64_bit_bytes, byte[] iv_64_bit_bytes, CipherType operation) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(key_64_bit_bytes);

        int input_length = input.length;
        if (input_length % 8 != 0) {
            throw new IllegalArgumentException("input's length is not valid");
        }

        byte[] output = new byte[input_length];
        byte[] input_block = new byte[8];

        byte[] iv = new byte[8];
        System.arraycopy(iv_64_bit_bytes, 0, iv, 0, 8);

        int times = input_length / 8;
        for (int i = 0; i < times; i++) {
            System.arraycopy(input, i * 8, input_block, 0, DES_BLOCK_SIZE);
            if (operation == CipherType.ENCRYPT) {
                byte[] xor_64_bit_bytes = DESUtils.xor(input_block, iv, 8);
                byte[] encrypted_bytes = DES.des_block_operate(xor_64_bit_bytes, key_64_bit_bytes, operation);
                System.arraycopy(encrypted_bytes, 0, output, i * 8, 8);
                System.arraycopy(encrypted_bytes, 0, iv, 0, 8);
            }
            if (operation == CipherType.DECRYPT) {
                byte[] decrypted_bytes = DES.des_block_operate(input_block, key_64_bit_bytes, operation);
                byte[] xor_64_bit_bytes = DESUtils.xor(decrypted_bytes, iv, 8);
                System.arraycopy(xor_64_bit_bytes, 0, output, i * 8, 8);
                System.arraycopy(input_block, 0, iv, 0, 8);
            }

        }
        return output;
    }
}
