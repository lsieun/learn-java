package lsieun.domain;

public class FieldInfo extends MemberInfo {

    public static final String NAME = "Field Info";


    @Override
    public String toString() {
        return NAME + ": {" +
                "startIndex='" + super.startIndex + "'" +
                ", length='" + super.length + "\', \r\n" +
                "\thexCode='" + super.hexCode + "\', \r\n" +
                "\tpattern='" + PATTERN + "\', \r\n" +
                "\taccessFlagsHexCode='0x" + super.accessFlagsHexCode + "'" +
                ", accessFlags='" + super.accessFlags + "\', \r\n" +
                "\tnameIndexHexCode='0x" + super.nameIndexHexCode + "'" +
                ", nameIndex='" + super.nameIndex + "'" +
                ", name='" + super.name + "\', \r\n" +
                "\tdescriptorIndexHexCode='0x" + super.descriptorIndexHexCode + "'" +
                ", descriptorIndex='" + super.descriptorIndex + "'" +
                ", descriptor='" + super.descriptor + "\', \r\n" +
                "\tattributesCountHexCode='0x" + super.attributesCountHexCode + "'" +
                ", attributesCount='" + super.attributesCount + "\', \r\n" +
                "\tvalue='" + super.value + "\'\r\n" +
                '}';
    }
}
