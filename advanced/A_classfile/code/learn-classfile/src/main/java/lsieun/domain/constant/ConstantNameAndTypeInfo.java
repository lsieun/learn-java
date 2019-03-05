package lsieun.domain.constant;

import java.util.ArrayList;
import java.util.List;

import lsieun.domain.InfoLevel;
import lsieun.utils.StringUtils;

@SuppressWarnings("Duplicates")
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
            list.add("nameIndex='" + this.nameIndex +"'");
            list.add("descriptorIndex='" + this.descriptorIndex +"'");
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
