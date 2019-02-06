package lsieun.domain;

public class MajorVersion extends Common {
    public static final int BYTE_COUNT = 2;
    public static final String NAME = "Major Version";

    @Override
    public String toString() {
        return NAME + ": {" +
                "startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                '}';
    }
}
