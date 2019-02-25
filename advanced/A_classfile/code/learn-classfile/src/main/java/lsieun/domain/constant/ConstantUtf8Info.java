package lsieun.domain.constant;

public class ConstantUtf8Info extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 3;
    public static final int LENGTH_BYTE_COUNT = 2;
    public static final String NAME = "UTF8";
    public static final String PATTERN = "tag(2)-length(4)-info";

    @Override
    public String toString() {
        return String.format("|%03d|", super.index) + " " + NAME + ": {" +
                "index='" + super.index + "'" +
                ", tagHex='0x" + super.tagHex + "'-->'(" + super.tag + ")'" +
                ", startIndex='" + super.startIndex + "'" +
                ", length='" + super.length + "'" +
                ", value='" + super.value + "'" +
                ", pattern='" + PATTERN + "'" +
                ", hexCode='" + super.hexCode + "'" +
                '}';
    }
}
