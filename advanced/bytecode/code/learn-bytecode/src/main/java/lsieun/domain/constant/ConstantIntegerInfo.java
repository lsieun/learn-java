package lsieun.domain.constant;

public class ConstantIntegerInfo extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 5;
    public static final int BYTES_BYTE_COUNT = 4;
    public static final String NAME = "Integer";
    public static final String PATTERN = "tag(2)-bytes(8)";

    private int intValue;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    @Override
    public String toString() {
        return String.format("|%03d|", super.index) + " " + NAME + ": {" +
                "index=" + super.index +
                ", tagHex=" + super.tagHex + "(" + super.tag + ")" +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", intValue=" + this.intValue +
                ", value='" + super.value + '\'' +
                ", pattern='" + PATTERN + '\'' +
                ", hexCode='" + super.hexCode + '\'' +
                '}';
    }
}
