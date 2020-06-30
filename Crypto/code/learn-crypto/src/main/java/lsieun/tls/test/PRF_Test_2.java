package lsieun.tls.test;

import lsieun.tls.utils.PRFUtils;
import lsieun.utils.ByteUtils;
import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// 这是验证335页的程序代码，结果正确
public class PRF_Test_2 {
    public static void main(String[] args) {
        byte[] secret = "secret".getBytes(StandardCharsets.UTF_8);
        byte[] label = "label".getBytes(StandardCharsets.UTF_8);
        byte[] seed = "seed".getBytes(StandardCharsets.UTF_8);
        int out_len = 20;


        byte[] bytes = PRFUtils.PRF(secret, label, seed, out_len);
        System.out.println(HexUtils.format(bytes, HexFormat.FORMAT_FF_SPACE_FF));

        String hex_str = "b5baf4722b91851a8816d22ebd8c1d8cc2e94d55";
        System.out.println(Arrays.equals(bytes, ByteUtils.fromHex(hex_str)));
    }
}
