package lsieun.domain.constant;

import java.util.ArrayList;
import java.util.List;

import lsieun.domain.InfoLevel;
import lsieun.utils.StringUtils;

@SuppressWarnings("Duplicates")
public class ConstantLongInfo extends ConstantCommonInfo {
    public static final int BYTE_COUNT = 9;
    public static final int BYTES_BYTE_COUNT = 8;
    public static final String NAME = "Long";
    public static final String PATTERN = "tag(2)-bytes(16)";

    private int longValue;

    public int getLongValue() {
        return longValue;
    }

    public void setLongValue(int longValue) {
        this.longValue = longValue;
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
            list.add("longValue='" + this.longValue +"'");
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
