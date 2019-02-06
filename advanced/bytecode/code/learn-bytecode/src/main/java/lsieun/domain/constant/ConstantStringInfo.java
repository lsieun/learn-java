package lsieun.domain.constant;

public class ConstantStringInfo extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 3;
    public static final int STRING_BYTE_COUNT = 2;
    public static final String NAME = "String";

    private int stringIndex;

    public int getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(int stringIndex) {
        this.stringIndex = stringIndex;
    }

    @Override
    public String toString() {
        return String.format("|%03d|", super.index) + " " + NAME + ": {" +
                "hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", index=" + super.index +
                ", tagHex=" + super.tagHex + "(" + super.tag + ")" +
                ", stringIndex=" + this.stringIndex +
                '}';
    }
}
