package lsieun.domain;

public class MethodContainer extends MemberContainer {
    public static final String NAME = "METHODS CONTAINER";

    @Override
    public String toString() {
        return NAME + ": {" +
                "hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                ", startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", methodsCount=" + super.count +
                '}';
    }
}
