package lsieun.crypto.des;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public class TripleDES_Verify {
    public static void main(String[] args) throws Exception {
        byte[] plain_text_bytes = "你知道吗？在.class文件中，Magic Number的值是0xcafebabe。".getBytes(StandardCharsets.UTF_8);
        byte[] key_bytes = "passwordverygoodlooklook".getBytes(StandardCharsets.UTF_8);
        byte[] initialization_vector_bytes = new byte[]{0x01, 0x12, 0x23, 0x34, 0x45, 0x56, 0x67, 0x78};

        // 加密
        byte[] encrypted_bytes1 = encrypt(plain_text_bytes, key_bytes, initialization_vector_bytes);
        byte[] encrypted_bytes2 = TripleDES.des_encrypt(plain_text_bytes, key_bytes, initialization_vector_bytes);
        System.out.println(Arrays.equals(encrypted_bytes1, encrypted_bytes2));

        // 解密
        byte[] decrypted_bytes1 = decrypt(encrypted_bytes1, key_bytes, initialization_vector_bytes);
        byte[] decrypted_bytes2 = TripleDES.des_decrypt(encrypted_bytes1, key_bytes, initialization_vector_bytes);
        System.out.println(Arrays.equals(decrypted_bytes1, decrypted_bytes2));

        System.out.println(new String(decrypted_bytes1, StandardCharsets.UTF_8));
    }

    public static byte[] encrypt(byte[] plain_text_bytes, byte[] key_bytes, byte[] iv_bytes)
            throws InvalidKeyException,
            NoSuchAlgorithmException,
            InvalidKeySpecException,
            NoSuchPaddingException,
            InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        DESedeKeySpec desKey = new DESedeKeySpec(key_bytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey secretKey = keyFactory.generateSecret(desKey);

        AlgorithmParameterSpec algParameters = new IvParameterSpec(iv_bytes);

        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, algParameters);
        return cipher.doFinal(plain_text_bytes);
    }

    public static byte[] decrypt(byte[] cipher_text_bytes, byte[] key_bytes, byte[] iv_bytes) throws
            InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        DESedeKeySpec desKey = new DESedeKeySpec(key_bytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey secretKey = keyFactory.generateSecret(desKey);

        AlgorithmParameterSpec algParameters = new IvParameterSpec(iv_bytes);

        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, algParameters);
        return cipher.doFinal(cipher_text_bytes);
    }
}
