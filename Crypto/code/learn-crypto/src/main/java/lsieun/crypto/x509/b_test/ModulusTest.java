package lsieun.crypto.x509.b_test;

import lsieun.utils.ByteUtils;
import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;

public class ModulusTest {
    public static void main(String[] args) {
        String str = "00:ba:78:0a:a2:67:32:81:77:f2:bb:60:30:f1:fe:" +
                "9d:0b:7e:54:2a:77:c9:37:d4:95:51:3e:2a:c5:8f:" +
                "b4:07:d2:50:69:e8:2a:ac:4b:ce:99:09:d5:22:d0:" +
                "01:01:15:27:f8:6b:79:0a:7e:88:0c:54:f4:5c:ca:" +
                "f6:b1:5a:8e:55";
        final String[] array = str.split(":");
        byte[] bytes = ByteUtils.fromHex(str, ":");
        System.out.println(HexUtils.format(bytes, HexFormat.FORMAT_FF_FF));
    }
}
