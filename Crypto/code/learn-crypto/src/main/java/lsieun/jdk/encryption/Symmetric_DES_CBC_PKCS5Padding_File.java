package lsieun.jdk.encryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

// https://examples.javacodegeeks.com/core-java/security/encrypt-decrypt-a-file-using-des/
public class Symmetric_DES_CBC_PKCS5Padding_File {
    static Cipher ce;
    static Cipher cd;

    public static void main(String args[]) throws Exception {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        SecretKey skey = KeyGenerator.getInstance("DES").generateKey();
        byte[] initializationVector = new byte[]{0x10, 0x10, 0x01, 0x04, 0x01, 0x01, 0x01, 0x02};

        AlgorithmParameterSpec algParameters = new IvParameterSpec(initializationVector);

        ce = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cd = Cipher.getInstance("DES/CBC/PKCS5Padding");

        ce.init(Cipher.ENCRYPT_MODE, skey, algParameters);
        cd.init(Cipher.DECRYPT_MODE, skey, algParameters);

        FileInputStream is = new FileInputStream("/home/liusen/output.txt");
        FileOutputStream os = new FileOutputStream("/home/liusen/output2.txt");

        int dataSize = is.available();
        byte[] inbytes = new byte[dataSize];
        is.read(inbytes);

        String str2 = new String(inbytes);
        System.out.println("Input file content: " + str2);

        write_encode(inbytes, os);

        os.flush();

        is.close();
        os.close();

        System.out.println("Ecrypted Content to output2.txt");

        is = new FileInputStream("/home/liusen/output2.txt");
        byte[] decBytes = new byte[dataSize];

        read_decode(decBytes, is);

        is.close();

        String str = new String(decBytes);

        System.out.println("Decrypted file contents: " + str);

    }

    public static void write_encode(byte[] bytes, OutputStream output) throws Exception {
        CipherOutputStream cOutputStream = new CipherOutputStream(output, ce);
        cOutputStream.write(bytes, 0, bytes.length);
        cOutputStream.close();
    }

    public static void read_decode(byte[] bytes, InputStream input) throws Exception {
        CipherInputStream cInputStream = new CipherInputStream(input, cd);
        int position = 0, i;
        while ((i = cInputStream.read()) != -1) {
            bytes[position] = (byte) i;
            position++;
        }
    }
}
