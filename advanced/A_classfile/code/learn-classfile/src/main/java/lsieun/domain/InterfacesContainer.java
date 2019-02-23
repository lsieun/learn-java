package lsieun.domain;

import java.util.ArrayList;
import java.util.List;

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
        return NAME + ": {" +
                "startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", count=" + this.count +
                ", hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                '}';
    }
}
