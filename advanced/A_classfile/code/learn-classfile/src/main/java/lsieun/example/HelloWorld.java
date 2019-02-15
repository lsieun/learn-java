package lsieun.example;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

public class HelloWorld implements Serializable, Closeable {
    public static final int INT_CONSTANT = 1;
    public static final long LONG_CONSTANT = 111L;
    public static final float FLOAT_CONSTANT = 3.14F;
    public static final double DOUBLE_CONSTANT = 9.99;
    private final String name;
    private int age;

    public HelloWorld() {
        this.name = "Tom小明啊";
        this.age = 10;
    }

    public HelloWorld(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        byte b = (byte)1; // 0001 1000
        int c = (b >> 0) & 0x01;
        System.out.println(c);

        System.out.println("Hello World!");
    }

    @Override
    public void close() throws IOException {
        System.out.println("Close");
    }
}
