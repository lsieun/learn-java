package lsieun.domain;

import java.util.ArrayList;
import java.util.List;

import lsieun.utils.StringUtils;

@SuppressWarnings("Duplicates")
public class MethodInfo extends MemberInfo {

    public static final String NAME = "Method Info";

    @Override
    public String toString() {
        List<String> list = new ArrayList();
        if(infoLevel.value() >= InfoLevel.ADVANCED.value()) {
            list.add("startIndex='" + super.startIndex +"'");
            list.add("length='" + super.length +"'");
        }

        if(infoLevel.value() >= InfoLevel.NORMAL.value()) {
            list.add("value='" + super.value +"'");
            list.add("accessFlags='" + super.accessFlags +"'");
            list.add("name='" + super.name +"'");
            list.add("descriptor='" + super.descriptor +"'");
        }

        if(infoLevel.value() >= InfoLevel.SIMPLE.value()) {
            list.add("accessFlagsHexCode='0x" + super.accessFlagsHexCode +"'");
            list.add("nameIndexHexCode='0x" + super.nameIndexHexCode + "(" + super.nameIndex +")'");
            list.add("descriptorIndexHexCode='0x" + super.descriptorIndexHexCode + "(" + super.descriptorIndex +")'");
            list.add("attributesCountHexCode='0x" + super.attributesCountHexCode + "(" + super.attributesCount +")'");
            list.add("pattern='" + PATTERN +"'");
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
