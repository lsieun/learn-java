package lsieun.crypto.provider;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SetProvider {
    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());
    }
}
