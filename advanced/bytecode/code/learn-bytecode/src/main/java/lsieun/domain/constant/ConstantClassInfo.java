package lsieun.domain.constant;

public class ConstantClassInfo extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 3;
    public static final int CLASS_BYTE_COUNT = 2;
    public static final String NAME = "CLASS";

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
                "hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", index=" + super.index +
                ", tagHex=" + super.tagHex + "(" + super.tag + ")" +
                ", classIndex=" + this.classIndex +
                '}';
    }
}
