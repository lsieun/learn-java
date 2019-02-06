package lsieun.domain;

import java.util.ArrayList;
import java.util.List;

public class FieldContainer extends Common {
    public static final String NAME = "FIELDS CONTAINER";

    private int fieldsCount;
    private List<FieldInfo> list;

    public FieldContainer() {
        this.list = new ArrayList<FieldInfo>();
    }

    public int getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    public List<FieldInfo> getList() {
        return list;
    }

    public void setList(List<FieldInfo> list) {
        this.list = list;
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
