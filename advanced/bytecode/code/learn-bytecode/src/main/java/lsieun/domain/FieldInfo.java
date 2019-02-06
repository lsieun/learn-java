package lsieun.domain;

public class FieldInfo extends Common {
    public static final int BYTE_COUNT = 8;
    public static final int ACCESS_FLAGS_BYTE_COUNT = 2;
    public static final int NAME_INDEX_BYTE_COUNT = 2;
    public static final int DESCRIPTOR_INDEX_BYTE_COUNT = 2;
    public static final int ATTRIBUTE_COUNT_BYTE_COUNT = 2;
    public static final String NAME = "Field Info";

    private String accessFlagsHexCode;
    private String accessFlags;
    private String nameIndexHexCode;
    private int nameIndex;
    private String name;
    private String descriptorIndexHexCode;
    private int descriptorIndex;
    private String descriptor;
    private String attributesCountHexCode;
    private int attributesCount;


    public String getAccessFlagsHexCode() {
        return accessFlagsHexCode;
    }

    public void setAccessFlagsHexCode(String accessFlagsHexCode) {
        this.accessFlagsHexCode = accessFlagsHexCode;
    }

    public String getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(String accessFlags) {
        this.accessFlags = accessFlags;
    }

    public String getNameIndexHexCode() {
        return nameIndexHexCode;
    }

    public void setNameIndexHexCode(String nameIndexHexCode) {
        this.nameIndexHexCode = nameIndexHexCode;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptorIndexHexCode() {
        return descriptorIndexHexCode;
    }

    public void setDescriptorIndexHexCode(String descriptorIndexHexCode) {
        this.descriptorIndexHexCode = descriptorIndexHexCode;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getAttributesCountHexCode() {
        return attributesCountHexCode;
    }

    public void setAttributesCountHexCode(String attributesCountHexCode) {
        this.attributesCountHexCode = attributesCountHexCode;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(int attributesCount) {
        this.attributesCount = attributesCount;
    }

    @Override
    public String toString() {
        return NAME + ": {" +
                "hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", accessFlagsHexCode=" + this.accessFlagsHexCode +
                ", accessFlags=" + this.accessFlags +
                ", nameIndexHexCode=" + this.nameIndexHexCode +
                ", nameIndex=" + this.nameIndex +
                ", name=" + this.name +
                ", descriptorIndexHexCode=" + this.descriptorIndexHexCode +
                ", descriptorIndex=" + this.descriptorIndex +
                ", descriptor=" + this.descriptor +
                ", attributesCountHexCode=" + this.attributesCountHexCode +
                ", attributesCount=" + this.attributesCount +
                '}';
    }
}
