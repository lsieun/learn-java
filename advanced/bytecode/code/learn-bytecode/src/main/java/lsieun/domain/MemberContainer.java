package lsieun.domain;

import java.util.ArrayList;
import java.util.List;

public class MemberContainer extends Common {
    protected int count;
    protected List<MemberInfo> list;

    public MemberContainer() {
        this.list = new ArrayList<MemberInfo>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MemberInfo> getList() {
        return list;
    }

    public void setList(List<MemberInfo> list) {
        this.list = list;
    }
}
