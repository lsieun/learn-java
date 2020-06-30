package lsieun.tls.entity;

public class ProtocolVersion {
    public final int major;
    public final int minor;

    public ProtocolVersion(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }
}
