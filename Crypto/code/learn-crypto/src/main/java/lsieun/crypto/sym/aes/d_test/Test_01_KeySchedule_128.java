package lsieun.crypto.sym.aes.d_test;

import lsieun.crypto.sym.aes.AESUtils;
import lsieun.utils.ByteUtils;

import java.util.List;

public class Test_01_KeySchedule_128 {
    public static void main(String[] args) {
        String cipher_key = "2b 7e 15 16 28 ae d2 a6 ab f7 15 88 09 cf 4f 3c";
        byte[] key_bytes = ByteUtils.fromHex(cipher_key, " ");

        List<byte[]> bytes_list = AESUtils.compute_key_schedule(key_bytes, 16);
        for (int i = 0; i < bytes_list.size(); i++) {
            byte[] bytes = bytes_list.get(i);
            String line = String.format("%02d: %s", i, ByteUtils.toHex(bytes));
            System.out.println(line);
        }
    }
}