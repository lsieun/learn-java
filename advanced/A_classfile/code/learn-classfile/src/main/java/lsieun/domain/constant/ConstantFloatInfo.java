package lsieun.domain.constant;

import java.util.ArrayList;
import java.util.List;

import lsieun.domain.InfoLevel;
import lsieun.utils.StringUtils;

@SuppressWarnings("Duplicates")
public class ConstantFloatInfo extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 5;
    public static final int BYTES_BYTE_COUNT = 4;
    public static final String NAME = "Float";
    public static final String PATTERN = "tag(2)-bytes(8)";

    private float floatValue;

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList();
        if(infoLevel.value() >= InfoLevel.ADVANCED.value()) {
            list.add("startIndex='" + super.startIndex +"'");
            list.add("length='" + super.length +"'");
        }

        if(infoLevel.value() >= InfoLevel.NORMAL.value()) {
            list.add("value='" + super.value +"'");
        }

        if(infoLevel.value() >= InfoLevel.SIMPLE.value()) {
            list.add("tagHex='0x" + super.tagHex + "(" + super.tag + ")'" );
            list.add("floatValue='" + this.floatValue +"'");
            list.add("pattern='" + PATTERN +"'");
            list.add("hexCode='0x" + super.hexCode +"'");

        }

        String content = StringUtils.list2str(list, ", ");

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|%03d|", super.index) + " " + NAME + ": {");
        sb.append(content);
        sb.append("}");

        return sb.toString();
    }
}
