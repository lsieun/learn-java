package lsieun.domain;

public class MagicNumber extends Common {
    public static final int BYTE_COUNT = 4;
    public static final String NAME = "Magic Number";

    @Override
    public void setHexCode(String hexCode) {
        super.setHexCode(hexCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(NAME + ": {");
        sb.append("}");
        return NAME + ": {" +
                "startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                '}';
    }
}
