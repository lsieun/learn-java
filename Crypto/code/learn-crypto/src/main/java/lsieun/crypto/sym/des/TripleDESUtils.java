package lsieun.crypto.sym.des;

import lsieun.crypto.sym.CipherType;

import java.util.Arrays;

public class TripleDESUtils {
    public static byte[] des_block_encrypt(byte[] input, byte[] key_192_bit_bytes) {
        return des_block_operate(input, key_192_bit_bytes, CipherType.ENCRYPT);
    }

    public static byte[] des_block_decrypt(byte[] input, byte[] key_192_bit_bytes) {
        return des_block_operate(input, key_192_bit_bytes, CipherType.DECRYPT);
    }

    public static byte[] des_block_operate(byte[] input_block, byte[] key_192_bit_bytes, CipherType operation) {
        int block_size = DESConst.DES_BLOCK_SIZE;
        byte[] key_block_1 = Arrays.copyOfRange(key_192_bit_bytes, 0, block_size);
        byte[] key_block_2 = Arrays.copyOfRange(key_192_bit_bytes, block_size, 2 * block_size);
        byte[] key_block_3 = Arrays.copyOfRange(key_192_bit_bytes, 2 * block_size, 3 * block_size);

        byte[] current_input_block = Arrays.copyOf(input_block, block_size);

        byte[] output_block;
        switch (operation) {
            case ENCRYPT:
                output_block = DESUtils.des_block_operate(current_input_block, key_block_1, CipherType.ENCRYPT);
                output_block = DESUtils.des_block_operate(output_block, key_block_2, CipherType.DECRYPT);
                output_block = DESUtils.des_block_operate(output_block, key_block_3, CipherType.ENCRYPT);
                break;
            case DECRYPT:
                output_block = DESUtils.des_block_operate(input_block, key_block_3, CipherType.DECRYPT);
                output_block = DESUtils.des_block_operate(output_block, key_block_2, CipherType.ENCRYPT);
                output_block = DESUtils.des_block_operate(output_block, key_block_1, CipherType.DECRYPT);
                break;
            default:
                throw new RuntimeException("Illegal operation: " + operation);
        }

        return output_block;
    }

}
