package lsieun.example;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

public class HelloWorld implements Serializable, Closeable {
    private final String name;
    private int age;

    public HelloWorld() {
        this.name = "Tom";
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
