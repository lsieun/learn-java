final class NumbericValue implements Comparable<NumbericValue> {
    private byte value;
    public NumbericValue(byte value) { this.value = value;  }
    public byte getValue() {return value; }
    public int compareTo(NumbericValue that) {return this.value - that.value; }
}
