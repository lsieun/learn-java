package lsieun.domain;

public class FieldContainer extends MemberContainer {
    public static final String NAME = "FIELD CONTAINER";

    @Override
    public String toString() {
        return NAME + ": {" +
                "startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", fieldsCount=" + super.count +
                ", hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                '}';
    }
}
