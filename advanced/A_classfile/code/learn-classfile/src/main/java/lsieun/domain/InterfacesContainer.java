package lsieun.domain;

import java.util.ArrayList;
import java.util.List;

import lsieun.utils.StringUtils;

@SuppressWarnings("Duplicates")
public class InterfacesContainer extends Common {
    public static final int BYTE_COUNT = 0;
    public static final String NAME = "Interfaces";

    private int count;
    private List<InterfaceInfo> interfaces;

    public InterfacesContainer() {
        this.interfaces = new ArrayList();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<InterfaceInfo> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<InterfaceInfo> interfaces) {
        this.interfaces = interfaces;
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
            list.add("count='" + this.count +"'");
            //list.add("hexCode='0x" + super.hexCode +"'");
        }

        if(infoLevel.value() >= InfoLevel.ADVANCED.value()) {
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
