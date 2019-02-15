package lsieun.domain.constant;

public class ConstantStringInfo extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 3;
    public static final int STRING_BYTE_COUNT = 2;
    public static final String NAME = "String";
    public static final String PATTERN = "tag(2)-utf8Index(4)";

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
                "index=" + super.index +
                ", tagHex=" + super.tagHex + "(" + super.tag + ")" +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", stringIndex=" + this.stringIndex +
                ", value='" + super.value + '\'' +
                ", pattern='" + PATTERN + '\'' +
                ", hexCode='" + super.hexCode + '\'' +
                '}';
    }
}
