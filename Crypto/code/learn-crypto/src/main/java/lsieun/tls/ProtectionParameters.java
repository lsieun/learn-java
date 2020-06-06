package lsieun.tls;

public class ProtectionParameters {
    public byte[] mac_secret;
    public byte[] key;
    public byte[] iv;
    public CipherSuiteIdentifier suite = CipherSuiteIdentifier.TLS_NULL_WITH_NULL_NULL;

    public long seq_num = 0;
}
