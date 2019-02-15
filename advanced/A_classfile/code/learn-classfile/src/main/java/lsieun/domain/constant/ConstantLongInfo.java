package lsieun.domain.constant;

public class ConstantLongInfo extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 9;
    public static final int BYTES_BYTE_COUNT = 8;
    public static final String NAME = "Long";
    public static final String PATTERN = "tag(2)-bytes(16)";

    private int longValue;

    public int getLongValue() {
        return longValue;
    }

    public void setLongValue(int longValue) {
        this.longValue = longValue;
    }

    @Override
    public String toString() {
        return String.format("|%03d|", super.index) + " " + NAME + ": {" +
                "index=" + super.index +
                ", tagHex=" + super.tagHex + "(" + super.tag + ")" +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", longValue=" + this.longValue +
                ", value='" + super.value + '\'' +
                ", pattern='" + PATTERN + '\'' +
                ", hexCode='" + super.hexCode + '\'' +
                '}';
    }
}
