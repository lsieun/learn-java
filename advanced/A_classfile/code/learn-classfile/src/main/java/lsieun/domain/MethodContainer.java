package lsieun.domain;

public class MethodContainer extends MemberContainer {
    public static final String NAME = "METHOD CONTAINER";

    @Override
    public String toString() {
        return NAME + ": {" +
                "startIndex=" + super.startIndex +
                ", length=" + super.length +
                ", methodsCount=" + super.count +
                ", hexCode='" + super.hexCode + '\'' +
                ", value='" + super.value + '\'' +
                '}';
    }
}
