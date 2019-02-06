package lsieun.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lsieun.domain.constant.ConstantCommonInfo;

public class ConstantPoolInfo extends Common {
    public static final String NAME = "CONSTANT POOL";

    private int count;
    private Map<Integer, ConstantCommonInfo> map;
    private List<ConstantCommonInfo> list;

    public ConstantPoolInfo() {
        this.count = 0;
        this.map = new HashMap<Integer, ConstantCommonInfo>();
        this.list = new ArrayList<ConstantCommonInfo>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Map<Integer, ConstantCommonInfo> getMap() {
        return map;
    }

    public void setMap(Map<Integer, ConstantCommonInfo> map) {
        this.map = map;
    }

    public List<ConstantCommonInfo> getList() {
        return list;
    }

    public void setList(List<ConstantCommonInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return NAME + ": {" +
                "hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", count=" + this.count +
                '}';
    }
}
