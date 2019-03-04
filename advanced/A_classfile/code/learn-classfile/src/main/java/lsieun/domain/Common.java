package lsieun.domain;

public class Common {
    public static InfoLevel infoLevel = InfoLevel.SIMPLE;

    protected int startIndex;
    protected int length;
    protected String hexCode;
    protected String value;


    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getNextIndex() {
        return this.startIndex + length;
    }
}
