package lsieun.crypto.sym.des;

import lsieun.crypto.sym.CipherType;
import lsieun.utils.ByteUtils;

public class DESDetailsTest {
    public static void main(String[] args) {
        byte[] input_bytes = DESSample.input;
        byte[] key_bytes = DESSample.key;

        byte[] encrypted_bytes = DESDetails.des_block_operate(input_bytes, key_bytes, CipherType.ENCRYPT);
        byte[] decrypted_bytes = DESDetails.des_block_operate(encrypted_bytes, key_bytes, CipherType.DECRYPT);

        System.out.println(ByteUtils.toHex(encrypted_bytes));
        System.out.println(ByteUtils.toHex(decrypted_bytes));
    }
}