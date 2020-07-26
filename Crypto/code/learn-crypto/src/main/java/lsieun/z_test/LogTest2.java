package lsieun.z_test;

import lsieun.crypto.sym.CBCUtils;
import lsieun.crypto.sym.aes.AESUtils;
import lsieun.tls.utils.SecretUtils;
import lsieun.utils.ByteDashboard;
import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;
import lsieun.utils.SSLLog;

public class LogTest2 {
    public static void main(String[] args) {
        String filename = "log/tlsv1.1_rsa_aes_256_cbc_sha1_baidu.txt";
        byte[] pre_master_secret = SSLLog.read_data(filename, "958-960");
        byte[] client_random = SSLLog.read_data(filename, "963-964");
        byte[] server_random = SSLLog.read_data(filename, "966-967");
        byte[] master_secret = SecretUtils.calculate_master_secret(pre_master_secret, client_random, server_random);
        byte[] key_material = SecretUtils.calculate_keys(104, master_secret, client_random, server_random);

        ByteDashboard bd = new ByteDashboard(key_material);
        byte[] client_mac_secret = bd.nextN(20);
        byte[] server_mac_secret = bd.nextN(20);
        byte[] client_key = bd.nextN(32);
        byte[] server_key = bd.nextN(32);
//        System.out.println(HexUtils.format(client_mac_secret, " ", 16));
//        System.out.println(HexUtils.format(server_mac_secret, " ", 16));
//        System.out.println(HexUtils.format(client_key, " ", 16));
//        System.out.println(HexUtils.format(server_key, " ", 16));

        byte[] verify_data = HexUtils.parse("AA 00 6F E3 04 F5 8F 30 EC DA F6 A3", HexFormat.FORMAT_FF_SPACE_FF);
        System.out.println("verify data:");
        for (byte b : verify_data) {
            System.out.print((b & 0xFF) + ", ");
        }
        System.out.println();
        System.out.println();

        byte[] plain_iv = SSLLog.read_data(filename, "1001-1001");
        System.out.println("plain_iv:");
        System.out.println(HexUtils.format(plain_iv, " ", 16));
        System.out.println();

        byte[] bytes = CBCUtils.cbc_encrypt(plain_iv, client_key, plain_iv, 16, AESUtils::aes_block_encrypt);
        System.out.println("bytes:");
        System.out.println(HexUtils.format(bytes, " ", 16));
        System.out.println();

        byte[] encrypted_iv = HexUtils.parse("23 1C 56 92 1F D7 B4 A4 D4 47 8B 21 67 28 4F 88", HexFormat.FORMAT_FF_SPACE_FF);
        System.out.println("encrypted_iv:");
        System.out.println(HexUtils.format(encrypted_iv, " ", 16));
        System.out.println();

//        byte[] input = SSLLog.read_data(filename, "1002-1004");
//        System.out.println("input:");
//        System.out.println(HexUtils.format(input, " ", 16));
//        System.out.println();
//
//
//        byte[] encrypted_bytes = CBCUtils.cbc_encrypt(input, client_key, encrypted_iv, 16, AESUtils::aes_block_encrypt);
//        System.out.println("encrypted:");
//        System.out.println(HexUtils.format(encrypted_bytes, " ", 16));
//        System.out.println();
//
//        byte[] decrypted_iv = HexUtils.parse("23 1C 56 92 1F D7 B4 A4 D4 47 8B 21 67 28 4F 88", HexFormat.FORMAT_FF_SPACE_FF);
//        System.out.println("decrypted_iv:");
//        System.out.println(HexUtils.format(decrypted_iv, " ", 16));
//        System.out.println();
//
//        byte[] decrypted_bytes = CBCUtils.cbc_decrypt(encrypted_bytes, client_key, decrypted_iv, 16, AESUtils::aes_block_decrypt);
//        System.out.println("decrypted:");
//        System.out.println(HexUtils.format(decrypted_bytes, " ", 16));
//        System.out.println();
    }
}
