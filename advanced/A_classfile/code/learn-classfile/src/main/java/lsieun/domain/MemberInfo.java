package lsieun.domain;


public class MemberInfo extends Common {
    public static final int BYTE_COUNT = 8;
    public static final int ACCESS_FLAGS_BYTE_COUNT = 2;
    public static final int NAME_INDEX_BYTE_COUNT = 2;
    public static final int DESCRIPTOR_INDEX_BYTE_COUNT = 2;
    public static final int ATTRIBUTE_COUNT_BYTE_COUNT = 2;
    public static final String PATTERN = "accessFlags(4)-nameIndex(4)-descriptorIndex(4)-attributesCount(4)-attributes";


    protected String accessFlagsHexCode;
    protected String accessFlags;
    protected String nameIndexHexCode;
    protected int nameIndex;
    protected String name;
    protected String descriptorIndexHexCode;
    protected int descriptorIndex;
    protected String descriptor;
    protected String attributesCountHexCode;
    protected int attributesCount;
    protected AttributeContainer attributeContainer;

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

    public AttributeContainer getAttributeContainer() {
        return attributeContainer;
    }

    public void setAttributeContainer(AttributeContainer attributeContainer) {
        this.attributeContainer = attributeContainer;
    }
}
