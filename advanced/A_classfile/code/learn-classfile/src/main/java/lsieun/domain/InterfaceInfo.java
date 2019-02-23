package lsieun.domain;

public class InterfaceInfo extends Common {
    public static final int BYTE_COUNT = 2;
    public static final String NAME = "Interface";

    private int classIndex;

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    @Override
    public String toString() {
        return NAME + ": {" +
                "startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", classIndex=" + this.classIndex +
                ", hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                '}';
    }
}
