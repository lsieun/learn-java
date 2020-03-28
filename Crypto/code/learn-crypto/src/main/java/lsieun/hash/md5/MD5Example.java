package lsieun.hash.md5;

import java.nio.charset.StandardCharsets;

public class MD5Example {
    // f29939a25efabaef3b87e2cbfe641315
    public static final byte[] input_52_bytes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".getBytes(StandardCharsets.UTF_8);

    // 27eca74a76daae63f472b250b5bcff9d
    public static final byte[] input_56_bytes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123".getBytes(StandardCharsets.UTF_8);

    // 1e7cffb80e71c26369bf479f37736f7e
    public static final byte[] input_64_bytes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz012345678901".getBytes(StandardCharsets.UTF_8);

    // b5ad31712f8d73f590014f172fcaae86
    public static final byte[] input_70_bytes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz012345678901234567".getBytes(StandardCharsets.UTF_8);

    public static final String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    // 79054025255fb1a26e4bc422aef54eb4
    public static final String collision_str_1 =
            "d131dd02c5e6eec4693d9a0698aff95c2fcab58712467eab4004583eb8fb7f89" +
                    "55ad340609f4b30283e488832571415a085125e8f7cdc99fd91dbdf280373c5b" +
                    "d8823e3156348f5bae6dacd436c919c6dd53e2b487da03fd02396306d248cda0" +
                    "e99f33420f577ee8ce54b67080a80d1ec69821bcb6a8839396f9652b6ff72a70";

    // 79054025255fb1a26e4bc422aef54eb4
    public static final String collision_str_2 =
            "d131dd02c5e6eec4693d9a0698aff95c2fcab50712467eab4004583eb8fb7f89" +
                    "55ad340609f4b30283e4888325f1415a085125e8f7cdc99fd91dbd7280373c5b" +
                    "d8823e3156348f5bae6dacd436c919c6dd53e23487da03fd02396306d248cda0" +
                    "e99f33420f577ee8ce54b67080280d1ec69821bcb6a8839396f965ab6ff72a70";
}
