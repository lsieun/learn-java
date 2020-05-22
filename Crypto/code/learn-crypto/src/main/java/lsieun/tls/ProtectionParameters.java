package lsieun.tls;

public class ProtectionParameters {
    public byte[] mac_secret;
    public byte[] key;
    public byte[] iv;
    public CipherSuiteIdentifier suite;

}
