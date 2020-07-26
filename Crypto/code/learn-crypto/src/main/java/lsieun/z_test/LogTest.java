package lsieun.z_test;

import lsieun.utils.SSLLog;

public class LogTest {
    public static void main(String[] args) {
        String filename = "log/tlsv1.1_rsa_aes_256_cbc_sha1_baidu.txt";
        SSLLog log = new SSLLog(filename);
        log.run();
    }
}
