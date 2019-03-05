package lsieun.domain;

import java.util.ArrayList;
import java.util.List;

import lsieun.utils.StringUtils;

@SuppressWarnings("Duplicates")
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
        List<String> list = new ArrayList();
        if(infoLevel.value() >= InfoLevel.ADVANCED.value()) {
            list.add("startIndex='" + super.startIndex +"'");
            list.add("length='" + super.length +"'");
        }

        if(infoLevel.value() >= InfoLevel.NORMAL.value()) {
            list.add("value='" + super.value +"'");
            //list.add("attributeName='" + this.attributeName +"'");
        }

        if(infoLevel.value() >= InfoLevel.SIMPLE.value()) {
            list.add("attributeNameIndexHexCode='0x" + this.attributeNameIndexHexCode + "(" + this.attributeNameIndex +")'");
            list.add("attributeLengthHexCode='0x" + this.attributeLengthHexCode + "(" + this.attributeLength +")'");

            list.add("pattern='" + PATTERN +"'");
            list.add("hexCode='0x" + super.hexCode +"'");
        }

        String content = StringUtils.list2str(list, ", ");

        StringBuilder sb = new StringBuilder();
        sb.append(NAME + ": {");
        sb.append(content);
        sb.append("}");
        return sb.toString();
    }

}
