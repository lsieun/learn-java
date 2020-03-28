package lsieun.crypto.sym.aes.d_test;

import lsieun.crypto.sym.aes.AESConst;
import lsieun.crypto.sym.aes.AESUtils;
import lsieun.utils.ByteUtils;

@SuppressWarnings("Duplicates")
public class Test_02_Encryption_256 {
    public static void main(String[] args) {
        byte[] key_bytes = ByteUtils.fromHex("00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f 10 11 12 13 14 15 16 17 18 19 1a 1b 1c 1d 1e 1f", " ");
        byte[] plain_text_bytes = ByteUtils.fromHex("00 11 22 33 44 55 66 77 88 99 aa bb cc dd ee ff", " ");

        byte[] encrypted_bytes = new byte[AESConst.AES_BLOCK_SIZE];
        AESUtils.aes_block_encrypt(plain_text_bytes, encrypted_bytes, key_bytes, 32);
        System.out.println(ByteUtils.toHex(encrypted_bytes));
        System.out.println("==============================");

        byte[] decrypted_bytes = new byte[AESConst.AES_BLOCK_SIZE];
        AESUtils.aes_block_decrypt(encrypted_bytes, decrypted_bytes, key_bytes, 32);
        System.out.println(ByteUtils.toHex(decrypted_bytes));
        System.out.println(ByteUtils.toHex(plain_text_bytes));
    }
}
