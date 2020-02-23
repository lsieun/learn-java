package lsieun.bit.utils;

public class Pair {
    public final String first;
    public final String second;

    public Pair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public Pair(int first, String second) {
        this.first = String.valueOf(first);
        this.second = second;
    }
}
