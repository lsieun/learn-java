package lsieun.domain.constant;

public class ConstantNameAndTypeInfo extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 5;
    public static final int NAME_BYTE_COUNT = 2;
    public static final int DESCRIPTOR_BYTE_COUNT = 2;
    public static final String NAME = "Name And Type";
    public static final String PATTERN = "tag(2)-nameIndex(4)-descriptorIndex(4)";

    private int nameIndex;
    private int descriptorIndex;

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    @Override
    public String toString() {
        return String.format("|%03d|", super.index) + " " + NAME + ": {" +
                "index=" + super.index +
                ", tagHex=" + super.tagHex + "(" + super.tag + ")" +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", nameIndex=" + this.nameIndex +
                ", descriptorIndex=" + this.descriptorIndex +
                ", value='" + super.value + '\'' +
                ", pattern='" + PATTERN + '\'' +
                ", hexCode='" + super.hexCode + '\'' +
                '}';
    }
}