package lsieun.domain.constant;

public class ConstantFloatInfo extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 5;
    public static final int BYTES_BYTE_COUNT = 4;
    public static final String NAME = "Float";
    public static final String PATTERN = "tag(2)-bytes(8)";

    private float floatValue;

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    @Override
    public String toString() {
        return String.format("|%03d|", super.index) + " " + NAME + ": {" +
                "index=" + super.index +
                ", tagHex=" + super.tagHex + "(" + super.tag + ")" +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", floatValue=" + this.floatValue +
                ", value='" + super.value + '\'' +
                ", pattern='" + PATTERN + '\'' +
                ", hexCode='" + super.hexCode + '\'' +
                '}';
    }
}
