package lsieun.crypto.asym.rsa.e_example;

import java.math.BigInteger;

public class AES_1024_Example {
    public static final BigInteger n = new BigInteger("145906768007583323230186939349070635292401872375357164399581871019873438799005358938369571402670149802121818086292467422828157022922076746906543401224889672472407926969987100581290103199317858753663710862357656510507883714297115637342788911463535102712032765166518411726859837988672111837205085526346618740053");
    public static final BigInteger e = new BigInteger("65537");
    public static final BigInteger d = new BigInteger("89489425009274444368228545921773093919669586065884257445497854456487674839629818390934941973262879616797970608917283679875499331574161113854088813275488110588247193077582527278437906504015680623423550067240042466665654232383502922215493623289472138866445818789127946123407807725702626644091036502372545139713");

    public static void main(String[] args) {
        BigInteger msg = new BigInteger("123456");  // Any integer in the range [0, n)

        // Message encryption
        BigInteger encrypted_num = msg.modPow(e, n);

        // Message decryption
        BigInteger decrypted_num = encrypted_num.modPow(d, n);
        System.out.println(decrypted_num);
    }
}
