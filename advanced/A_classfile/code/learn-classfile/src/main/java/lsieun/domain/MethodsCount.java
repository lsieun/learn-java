package lsieun.domain;

public class MethodsCount extends Common {
    public static final int BYTE_COUNT = 2;
    public static final String NAME = "Methods Count";

    private int methodsCount;

    public int getMethodsCount() {
        return methodsCount;
    }

    public void setMethodsCount(int methodsCount) {
        this.methodsCount = methodsCount;
    }

    @Override
    public String toString() {
        return NAME + ": {" +
                "hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", methodsCount=" + this.methodsCount +
                '}';
    }
}
