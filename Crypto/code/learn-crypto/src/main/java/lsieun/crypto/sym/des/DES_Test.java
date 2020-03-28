package lsieun.crypto.sym.des;

import lsieun.utils.ByteUtils;

public class DES_Test {
    public static void main(String[] args) {
        byte[] key_64_bit_bytes = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
//        byte[] msg_64_bit_bytes = {'c', 'a', 'f', 'e', 'b', 'a', 'b', 'e'};
        byte[] msg_64_bit_bytes = {'a', 'b', 'c', 'd', 'e', 'f', (byte) 128, 0};

        byte[] encrypt_bytes = DES.des_block_operate(msg_64_bit_bytes, key_64_bit_bytes, CipherType.ENCRYPT);
        byte[] decrypt_bytes = DES.des_block_operate(encrypt_bytes, key_64_bit_bytes, CipherType.DECRYPT);

        System.out.println(ByteUtils.toHex(encrypt_bytes));
        System.out.println(ByteUtils.toBinary(encrypt_bytes));
        System.out.println(ByteUtils.toBinary(decrypt_bytes));
        System.out.println(ByteUtils.toBinary(msg_64_bit_bytes));
    }
}
