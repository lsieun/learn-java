package lsieun.crypto.sym.des;

import lsieun.utils.ByteUtils;

public class DESSample {
    public static final byte[] plain_text_bytes = {
            (byte) 0x87, (byte) 0x87, (byte) 0x87, (byte) 0x87,
            (byte) 0x87, (byte) 0x87, (byte) 0x87, (byte) 0x87
    };

    public static final byte[] key_bytes = {
            (byte) 0x0E, (byte) 0x32, (byte) 0x92, (byte) 0x32,
            (byte) 0xEA, (byte) 0x6D, (byte) 0x0D, (byte) 0x73
    };

    public static void main(String[] args) {
//        byte[] encrypted_msg = DES.des_block_operate(plain_text_bytes, key_bytes, CipherType.ENCRYPT);
        byte[] encrypted_msg = DES.des_encrypt_pkcs5(plain_text_bytes, key_bytes);
        System.out.println(ByteUtils.toHex(encrypted_msg));
    }
}
