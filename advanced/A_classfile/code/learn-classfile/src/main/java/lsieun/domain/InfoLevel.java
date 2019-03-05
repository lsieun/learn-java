package lsieun.domain;

public enum InfoLevel {
    SIMPLE(1),
    NORMAL(2),
    ADVANCED(3);

    private int value = 0;

    private InfoLevel(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
