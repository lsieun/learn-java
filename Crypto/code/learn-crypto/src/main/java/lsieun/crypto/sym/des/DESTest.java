package lsieun.crypto.sym.des;

import lsieun.crypto.sym.CipherType;
import lsieun.utils.ByteUtils;

import java.util.Arrays;

public class DESTest {
    public static void main(String[] args) {
        byte[] input = DESSample.input;
        byte[] key = DESSample.key;
        byte[] output = DESSample.output;

        byte[] encrypted_bytes = DESUtils.des_block_operate(input, key, CipherType.ENCRYPT);
        byte[] decrypted_bytes = DESUtils.des_block_operate(encrypted_bytes, key, CipherType.DECRYPT);

        System.out.println(Arrays.equals(output, encrypted_bytes));
        System.out.println(ByteUtils.toHex(encrypted_bytes));
        System.out.println(ByteUtils.toHex(decrypted_bytes));
        System.out.println(ByteUtils.toHex(input));
    }
}
