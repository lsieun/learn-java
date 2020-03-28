package lsieun.crypto.sym.aes.d_test;

import lsieun.crypto.sym.aes.AESUtils;
import lsieun.utils.ByteUtils;

import java.util.List;

public class Test_01_KeySchedule_192 {
    public static void main(String[] args) {
        String cipher_key = "8e 73 b0 f7 da 0e 64 52 c8 10 f3 2b 80 90 79 e5 62 f8 ea d2 52 2c 6b 7b";
        byte[] key_bytes = ByteUtils.fromHex(cipher_key, " ");

        List<byte[]> bytes_list = AESUtils.compute_key_schedule(key_bytes, 24);
        for (int i = 0; i < bytes_list.size(); i++) {
            byte[] bytes = bytes_list.get(i);
            String line = String.format("%02d: %s", i, ByteUtils.toHex(bytes));
            System.out.println(line);
        }
    }
}
