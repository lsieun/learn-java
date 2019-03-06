package lsieun.structure;

public enum ItemType {
    COMPOSITE(0),
    U1(1),
    U2(2),
    U4(4),
    U8(8);

    private int value;

    private ItemType(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
