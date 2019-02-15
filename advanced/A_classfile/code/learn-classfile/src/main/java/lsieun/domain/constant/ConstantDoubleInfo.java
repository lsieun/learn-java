package lsieun.domain.constant;

public class ConstantDoubleInfo extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 9;
    public static final int BYTES_BYTE_COUNT = 8;
    public static final String NAME = "Double";
    public static final String PATTERN = "tag(2)-bytes(16)";

    private double doubleValue;

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    @Override
    public String toString() {
        return String.format("|%03d|", super.index) + " " + NAME + ": {" +
                "index=" + super.index +
                ", tagHex=" + super.tagHex + "(" + super.tag + ")" +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", doubleValue=" + this.doubleValue +
                ", value='" + super.value + '\'' +
                ", pattern='" + PATTERN + '\'' +
                ", hexCode='" + super.hexCode + '\'' +
                '}';
    }
}
