package lsieun.crypto.sym.des;

import lsieun.crypto.sym.CipherType;
import lsieun.crypto.sym.ECBUtils;
import lsieun.utils.ByteUtils;
import lsieun.utils.PaddingUtils;

import java.util.Arrays;

public class DESTest_PKCS5Padding {
    public static void main(String[] args) {
        byte[] input = DESSample.input;
        byte[] key = DESSample.key;

        byte[] padded_input = PaddingUtils.add_pkcs5_padding(input, DESConst.DES_BLOCK_SIZE);
        byte[] encrypted_bytes = DESUtils.des_operate(padded_input, key, CipherType.ENCRYPT);
        byte[] encrypted_bytes2 = ECBUtils.ecb_encrypt(padded_input, key, 8, DESUtils::des_block_encrypt);

        System.out.println(ByteUtils.toHex(encrypted_bytes));
        System.out.println(ByteUtils.toHex(encrypted_bytes2));

        byte[] decrypted_bytes = DESUtils.des_operate(encrypted_bytes, key, CipherType.DECRYPT);
        byte[] decrypted_bytes2 = ECBUtils.ecb_decrypt(encrypted_bytes2, key, 8, DESUtils::des_block_decrypt);
        byte[] remove_pad_bytes = PaddingUtils.remove_pkcs5_padding(decrypted_bytes2);
        System.out.println(ByteUtils.toHex(remove_pad_bytes));

        System.out.println(Arrays.equals(decrypted_bytes, decrypted_bytes2));
    }
}