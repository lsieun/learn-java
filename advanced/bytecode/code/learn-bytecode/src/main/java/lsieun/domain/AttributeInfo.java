package lsieun.domain;

public class AttributeInfo extends Common {
    public static final int BYTE_COUNT = 6;
    public static final int ATTRIBUTE_NAME_INDEX_BYTE_COUNT = 2;
    public static final int ATTRIBUTE_LENGTH_BYTE_COUNT = 4;
    public static final String NAME = "Attribute Info";
    public static final String PATTERN = "attributeNameIndex(4)-attributeLength(8)-info";

    protected String attributeNameIndexHexCode;
    protected int attributeNameIndex;
    protected String attributeName;
    protected String attributeLengthHexCode;
    protected int attributeLength;

    public String getAttributeNameIndexHexCode() {
        return attributeNameIndexHexCode;
    }

    public void setAttributeNameIndexHexCode(String attributeNameIndexHexCode) {
        this.attributeNameIndexHexCode = attributeNameIndexHexCode;
    }

    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public void setAttributeNameIndex(int attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeLengthHexCode() {
        return attributeLengthHexCode;
    }

    public void setAttributeLengthHexCode(String attributeLengthHexCode) {
        this.attributeLengthHexCode = attributeLengthHexCode;
    }

    public int getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(int attributeLength) {
        this.attributeLength = attributeLength;
    }

    @Override
    public String toString() {
        return NAME + ": {" +
                "startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", value='" + super.value + '\'' +
                ", attributeNameIndexHexCode=" + this.attributeNameIndexHexCode +
                ", attributeNameIndex=" + this.attributeNameIndex +
                ", attributeName=" + this.attributeName +
                ", attributeLengthHexCode=" + this.attributeLengthHexCode +
                ", attributeLength=" + this.attributeLength +
                ", pattern='" + PATTERN + '\'' +
                ", hexCode='" + super.hexCode + '\'' +
                '}';
    }

}
