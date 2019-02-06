package lsieun.domain;

public class ClassInfo extends Common {
    public static final int BYTE_COUNT = 8;
    public static final int ACCESS_FLAGS_BYTE_COUNT = 2;
    public static final int THIS_CLASS_BYTE_COUNT = 2;
    public static final int SUPER_CLASS_BYTE_COUNT = 2;
    public static final int INTERFACES_COUNT_BYTE_COUNT = 2;
    public static final int INTERFACES_BYTE_COUNT = 2;
    public static final String NAME = "Class Info";

    public String accessFlagsHexCode;
    public String accessFlags;
    public String thisClassHexCode;
    public String thisClass;
    public String superClassHexCode;
    public String superClass;
    public String interfacesCountHexCode;
    public int interfacesCount;
    public String interfacesHexCode;
    public String interfaces;

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

    public String getThisClassHexCode() {
        return thisClassHexCode;
    }

    public void setThisClassHexCode(String thisClassHexCode) {
        this.thisClassHexCode = thisClassHexCode;
    }

    public String getThisClass() {
        return thisClass;
    }

    public void setThisClass(String thisClass) {
        this.thisClass = thisClass;
    }

    public String getSuperClassHexCode() {
        return superClassHexCode;
    }

    public void setSuperClassHexCode(String superClassHexCode) {
        this.superClassHexCode = superClassHexCode;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public String getInterfacesCountHexCode() {
        return interfacesCountHexCode;
    }

    public void setInterfacesCountHexCode(String interfacesCountHexCode) {
        this.interfacesCountHexCode = interfacesCountHexCode;
    }

    public int getInterfacesCount() {
        return interfacesCount;
    }

    public void setInterfacesCount(int interfacesCount) {
        this.interfacesCount = interfacesCount;
    }

    public String getInterfacesHexCode() {
        return interfacesHexCode;
    }

    public void setInterfacesHexCode(String interfacesHexCode) {
        this.interfacesHexCode = interfacesHexCode;
    }

    public String getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(String interfaces) {
        this.interfaces = interfaces;
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
                ", thisClassHexCode=" + this.thisClassHexCode +
                ", thisClass=" + this.thisClass +
                ", superClassHexCode=" + this.superClassHexCode +
                ", superClass=" + this.superClass +
                ", interfacesCountHexCode=" + this.interfacesCountHexCode +
                ", interfacesCount=" + this.interfacesCount +
                ", interfacesHexCode=" + this.interfacesHexCode +
                ", interfaces=" + this.interfaces +
                '}';
    }
}
