package lsieun.domain;

public class FieldsCount extends Common {
    public static final int BYTE_COUNT = 2;
    public static final String NAME = "Fields Count";

    private int fieldsCount;

    public int getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    @Override
    public String toString() {
        return NAME + ": {" +
                "hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", fieldsCount=" + this.fieldsCount +
                '}';
    }
}
