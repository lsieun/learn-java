package lsieun.domain;

public class MethodInfo extends MemberInfo {

    public static final String NAME = "Method Info";

    @Override
    public String toString() {
        return NAME + ": {" +
                "startIndex='" + super.startIndex + "'" +
                ", length='" + super.length + "'" +
                "\thexCode='" + super.hexCode + "'\r\n" +
                "\tpattern='" + PATTERN + "'\r\n" +
                "\taccessFlagsHexCode='" + super.accessFlagsHexCode + "'" +
                ", accessFlags='" + super.accessFlags + "'\r\n" +
                "\tnameIndexHexCode='" + super.nameIndexHexCode + "'" +
                ", nameIndex='" + super.nameIndex + "'" +
                ", name='" + super.name + "'\r\n" +
                "\tdescriptorIndexHexCode='" + super.descriptorIndexHexCode + "'" +
                ", descriptorIndex='" + super.descriptorIndex + "'" +
                ", descriptor='" + super.descriptor + "'\r\n" +
                "\tattributesCountHexCode='" + super.attributesCountHexCode + "'" +
                ", attributesCount='" + super.attributesCount + "'\r\n" +
                "\tvalue='" + super.value + "'\r\n" +
                '}';
    }
}
