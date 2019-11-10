package lsieun.generic.test;

public class NumberBox<T extends Number> extends Box<T> {
    public int intValue() {
        return value.intValue();
    }

}
