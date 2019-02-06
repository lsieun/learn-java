package lsieun.domain;

public class MethodInfo extends MemberInfo {

    public static final String NAME = "Method Info";

    @Override
    public String toString() {
        return NAME + ": {" +
                "startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", value='" + super.value + '\'' +
                ", accessFlagsHexCode=" + super.accessFlagsHexCode +
                ", accessFlags=" + super.accessFlags +
                ", nameIndexHexCode=" + super.nameIndexHexCode +
                ", nameIndex=" + super.nameIndex +
                ", name=" + super.name +
                ", descriptorIndexHexCode=" + super.descriptorIndexHexCode +
                ", descriptorIndex=" + super.descriptorIndex +
                ", descriptor=" + super.descriptor +
                ", attributesCountHexCode=" + super.attributesCountHexCode +
                ", attributesCount=" + super.attributesCount +
                ", pattern='" + PATTERN + '\'' +
                ", hexCode='" + super.hexCode + '\'' +
                '}';
    }
}
