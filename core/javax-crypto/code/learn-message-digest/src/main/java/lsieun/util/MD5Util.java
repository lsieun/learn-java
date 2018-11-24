package lsieun.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            String hashText = ByteUtil.toHex(messageDigest);
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String md5_2(String input) {
        try {
            byte[] bytesOfMessage = input.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(bytesOfMessage);

            String hashText = ByteUtil.toHex(messageDigest);
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String md5_3(String input) {
        try {
            byte[] bytesOfMessage = input.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(bytesOfMessage);
            byte[] messageDigest = md.digest();

            String hashText = ByteUtil.toHex(messageDigest);
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
