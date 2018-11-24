package lsieun.md;

import java.io.FileInputStream;
import java.security.MessageDigest;

// https://examples.javacodegeeks.com/core-java/security/messagedigest/generate-a-file-checksum-value-in-java/
// linux bash: sha1sum input.txt
public class SHA1FileCheckSum {
    public static void main(String args[]) throws Exception {

        String filepath = "/home/liusen/input.txt";

        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");

        FileInputStream fileInput = new FileInputStream(filepath);
        byte[] dataBytes = new byte[1024];

        int bytesRead = 0;

        while ((bytesRead = fileInput.read(dataBytes)) != -1) {
            messageDigest.update(dataBytes, 0, bytesRead);
        }
        byte[] digestBytes = messageDigest.digest();

        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < digestBytes.length; i++) {
            sb.append(Integer.toString((digestBytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Checksum for the File: " + sb.toString());

        fileInput.close();

    }
}
