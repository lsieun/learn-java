package lsieun.java8.stream_parallel.bean;

public class Accumulator {
    public long total = 0;

    public void add(long value) {
        total += value;
    }
}
