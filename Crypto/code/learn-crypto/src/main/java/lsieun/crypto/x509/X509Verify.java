package lsieun.crypto.x509;

import lsieun.crypto.asym.rsa.RSA;
import lsieun.crypto.asym.rsa.RSAKey;
import lsieun.crypto.asym.rsa.RSAUtils;
import lsieun.crypto.x509.asn1.ASN1Struct;
import lsieun.crypto.x509.asn1.ASN1Utils;
import lsieun.utils.ByteUtils;
import lsieun.utils.FileUtils;
import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.util.List;

public class X509Verify {
    public static void main(String[] args) {
        // Hash: DE 56 E0 8C 63 A8 EA 97 6C 21 AF BD 3D 8D 0B 03 BB 1F 95 35 D8 8E CC FA B5 CF 21 F9 C3 18 E3 6B
        // A3 FF 8F E0 0A F7 C9 33 39 12 3F E9 4E D5 2D 37 A5 42 B4 E3 AE 50 66 43 70 09 7F 65 AD 82 46 7E
        // A3 FF 8F E0 0A F7 C9 33 39 12 3F E9 4E D5 2D 37 A5 42 B4 E3 AE 50 66 43 70 09 7F 65 AD 82 46 7E
//        String hex_str = "90 FF 93 D0 D2 F2 1C C6 44 BC EC 58 C0 A0 F8 7A 9D 0B 5D CF 26 88 A7 1B C9 55 23 46 4D 36 C1 91 70 1C FC EE FA 82 3B 5F 9E FB 79 1C 69 48 A7 87 3F D6 27 01 BF 95 7B 6D CC 58 3A EB 13 66 79 92";
//        byte[] input = HexUtils.parse(hex_str, HexFormat.FORMAT_FF_FF);
//
        String modulus_str = "00:ba:78:0a:a2:67:32:81:77:f2:bb:60:30:f1:fe:" +
                "9d:0b:7e:54:2a:77:c9:37:d4:95:51:3e:2a:c5:8f:" +
                "b4:07:d2:50:69:e8:2a:ac:4b:ce:99:09:d5:22:d0:" +
                "01:01:15:27:f8:6b:79:0a:7e:88:0c:54:f4:5c:ca:" +
                "f6:b1:5a:8e:55";
        byte[] modulus_bytes = ByteUtils.fromHex(modulus_str, ":");
        BigInteger modulus = new BigInteger(1, modulus_bytes);
        BigInteger exponent = new BigInteger("65537");
        RSAKey rsaKey = new RSAKey(modulus, exponent);
//        byte[] bytes = RSAUtils.rsa_decrypt(input, rsaKey);
////        System.out.println(HexUtils.format(bytes, HexFormat.FORMAT_FF_FF));
//        ASN1Struct struct = ASN1Utils.asn1parse(bytes).get(0);
//        System.out.println(HexUtils.format(struct.children.get(0).header, HexFormat.FORMAT_FF_FF));
//        System.out.println(HexUtils.format(struct.children.get(0).data, HexFormat.FORMAT_FF_FF));
//        System.out.println(HexUtils.format(struct.children.get(1).data, HexFormat.FORMAT_FF_FF));

        String filepath = "/home/liusen/Workspace/tmp/cert.der";
        byte[] bytes = FileUtils.readBytes(filepath);

        final boolean flag = X509Utils.validate_certificate_rsa(bytes, rsaKey);
        System.out.println(flag);

    }
}
