package lsieun.domain.constant;

import lsieun.domain.Common;

public class ConstantCommonInfo extends Common {
    public static final int TAG_BYTE_COUNT = 1;

    protected String tagHex;
    protected int tag;
    // 注意：与startIndex区分开。
    // 这个index表示constant pool的索引值。
    // 而startIndex是在class文件中的索引值。
    protected int index;

    public String getTagHex() {
        return tagHex;
    }

    public void setTagHex(String tagHex) {
        this.tagHex = tagHex;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
