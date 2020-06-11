package lsieun.crypto.sym.des;

import lsieun.crypto.sym.CBCUtils;
import lsieun.utils.ByteUtils;

import java.util.Arrays;

// 这是书的59页的例子。
public class TripleDESTest_CBC {
    public static final String FORMAT = "%15s: %s";

    public static void main(String[] args) {
        byte[] input = TripleDESSample.input;
        byte[] key = TripleDESSample.key;
        byte[] iv = TripleDESSample.iv;
        byte[] output = TripleDESSample.output_cbc;

        int block_size = DESConst.DES_BLOCK_SIZE;
        byte[] encrypted_bytes = CBCUtils.cbc_encrypt(input, key, iv, block_size, TripleDESUtils::des_block_encrypt);
        byte[] decrypted_bytes = CBCUtils.cbc_decrypt(encrypted_bytes, key, iv, block_size, TripleDESUtils::des_block_decrypt);

        System.out.println(Arrays.equals(output, encrypted_bytes));
        System.out.println(String.format(FORMAT, "original bytes", ByteUtils.toHex(input)));
        System.out.println(String.format(FORMAT, "encrypted bytes", ByteUtils.toHex(encrypted_bytes)));
        System.out.println(String.format(FORMAT, "decrypted bytes", ByteUtils.toHex(decrypted_bytes)));

    }
}
