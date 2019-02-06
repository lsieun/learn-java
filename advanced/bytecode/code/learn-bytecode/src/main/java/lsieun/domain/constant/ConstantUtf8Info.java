package lsieun.domain.constant;

public class ConstantUtf8Info extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 3;
    public static final int LENGTH_BYTE_COUNT = 2;
    public static final String NAME = "UTF8";

    @Override
    public String toString() {
        return String.format("|%03d|", super.index) + " " + NAME + ": {" +
                "hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", index=" + super.index +
                ", tagHex=" + super.tagHex + "(" + super.tag + ")" +
                '}';
    }
}
