package lsieun.domain.constant;

public class ConstantClassInfo extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 3;
    public static final int CLASS_BYTE_COUNT = 2;
    public static final String NAME = "CLASS";
    public static final String PATTERN = "tag(2)-nameIndex(4)";

    private int classIndex;

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    @Override
    public String toString() {
        return String.format("|%03d|", super.index) + " " + NAME + ": {" +
                "index=" + super.index +
                ", tagHex=" + super.tagHex + "(" + super.tag + ")" +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", classIndex=" + this.classIndex +
                ", value='" + super.value + '\'' +
                ", pattern='" + PATTERN + '\'' +
                ", hexCode='" + super.hexCode + '\'' +
                '}';
    }
}
