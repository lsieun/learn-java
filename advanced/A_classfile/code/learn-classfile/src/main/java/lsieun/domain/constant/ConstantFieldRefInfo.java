package lsieun.domain.constant;

public class ConstantFieldRefInfo extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 5;
    public static final int CLASS_BYTE_COUNT = 2;
    public static final int NAME_AND_TYPE_BYTE_COUNT = 2;
    public static final String NAME = "Field Ref";
    public static final String PATTERN = "tag(2)-classIndex(4)-nameAndTypeIndex(4)";

    private int classIndex;
    private int nameAndTypeIndex;

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public String toString() {
        return String.format("|%03d|", super.index) + " " + NAME + ": {" +
                "index=" + super.index +
                ", tagHex=" + super.tagHex + "(" + super.tag + ")" +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", classIndex=" + this.classIndex +
                ", nameAndTypeIndex=" + this.nameAndTypeIndex +
                ", value='" + super.value + '\'' +
                ", pattern='" + PATTERN + '\'' +
                ", hexCode='" + super.hexCode + '\'' +
                '}';
    }
}
