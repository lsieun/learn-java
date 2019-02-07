package lsieun.domain;

public class FieldContainer extends MemberContainer {
    public static final String NAME = "FIELD CONTAINER";

    @Override
    public String toString() {
        return NAME + ": {" +
                "hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", fieldsCount=" + super.count +
                '}';
    }
}
