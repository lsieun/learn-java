package lsieun.generic.bean;

public class MyContained implements Contained {
    private final String name;

    public MyContained(String name) {
        this.name = name;
    }

    public @Override
    String toString() {
        return name;
    }
}
