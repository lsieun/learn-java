package lsieun.test;

import java.lang.invoke.MethodHandles;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import a.a.la;

public class Test01 {
    public static void main(String[] args) throws Exception {
        long var0 = la.a(8700240747601530565L, -8741273462026577332L, MethodHandles.lookup().lookupClass()).a(78900074440218L) ^ 116406085117963L;
        Cipher var2;
        Cipher var10000 = var2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
        byte[] var10003 = new byte[]{(byte)((int)(var0 >>> 56)), 0, 0, 0, 0, 0, 0, 0};

        for(int var3 = 1; var3 < 8; ++var3) {
            var10003[var3] = (byte)((int)(var0 << var3 * 8 >>> 56));
        }

        var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
        byte[] var4 = var2.doFinal("1aã8ùU\u001b¾Ò\u008eï\bB\u00102ðÏ\u0006\u0091?Ø6F\u008c\u008d\u0005\f)UGeT".getBytes("ISO-8859-1"));
        String var5 = b(var4).intern();
        //h = var5; // MD5withRSA
        System.out.println(var5);
    }

    private static String b(byte[] var0) {
        int var1 = 0;
        int var2;
        char[] var3 = new char[var2 = var0.length];

        for(int var4 = 0; var4 < var2; ++var4) {
            int var5;
            if ((var5 = 255 & var0[var4]) < 192) {
                var3[var1++] = (char)var5;
            } else {
                char var6;
                byte var7;
                if (var5 < 224) {
                    var6 = (char)((char)(var5 & 31) << 6);
                    ++var4;
                    var7 = var0[var4];
                    var6 |= (char)(var7 & 63);
                    var3[var1++] = var6;
                } else if (var4 < var2 - 2) {
                    var6 = (char)((char)(var5 & 15) << 12);
                    ++var4;
                    var7 = var0[var4];
                    var6 = (char)(var6 | (char)(var7 & 63) << 6);
                    ++var4;
                    var7 = var0[var4];
                    var6 |= (char)(var7 & 63);
                    var3[var1++] = var6;
                }
            }
        }

        return new String(var3, 0, var1);
    }
}
