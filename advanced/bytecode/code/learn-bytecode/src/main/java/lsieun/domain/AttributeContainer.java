package lsieun.domain;

import java.util.ArrayList;
import java.util.List;

public class AttributeContainer extends Common {
    public static final String NAME = "Attribute CONTAINER";

    protected int count;
    protected List<AttributeInfo> list;

    public AttributeContainer() {
        this.list = new ArrayList();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AttributeInfo> getList() {
        return list;
    }

    public void setList(List<AttributeInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return NAME + ": {" +
                "startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", count=" + this.count +
                ", hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                '}';
    }
}
