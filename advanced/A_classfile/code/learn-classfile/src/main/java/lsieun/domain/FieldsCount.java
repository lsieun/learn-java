package lsieun.domain;

import java.util.ArrayList;
import java.util.List;

import lsieun.utils.StringUtils;

@SuppressWarnings("Duplicates")
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
        List<String> list = new ArrayList();
        if(infoLevel.value() >= InfoLevel.ADVANCED.value()) {
            list.add("startIndex='" + super.startIndex +"'");
            list.add("length='" + super.length +"'");
        }

        if(infoLevel.value() >= InfoLevel.NORMAL.value()) {
            //list.add("value='" + super.value +"'");
        }

        if(infoLevel.value() >= InfoLevel.SIMPLE.value()) {
            list.add("fieldsCount='" + this.fieldsCount +"'");
            list.add("hexCode='0x" + super.hexCode +"'");
        }

        String content = StringUtils.list2str(list, ", ");

        StringBuilder sb = new StringBuilder();
        sb.append(NAME + ": {");
        sb.append(content);
        sb.append("}");
        return sb.toString();
    }
}
