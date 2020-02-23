package lsieun.bit.utils;

public enum BitOp {
    /**
     * <pre>1010 & 0011 = 0010</pre>
     */
    AND("&"),
    /**
     * <pre>1010 ^ 0011 = 1001</pre>
     */
    EXCLUSIVE_OR("^"),
    /**
     * <pre>1010 | 0011 = 1011</pre>
     */
    INCLUSIVE_OR("|"),
    /**
     * <pre>~1010 = 0101</pre>
     */
    COMPLEMENT("~");

    public final String val;

    BitOp(String val) {
        this.val = val;
    }
}
