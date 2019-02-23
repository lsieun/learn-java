package lsieun.domain;

public class InterfacesCount extends Common {
    public static final int BYTE_COUNT = 2;
    public static final String NAME = "Interfaces Count";

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
