package lsieun.except;

import java.io.*;
import java.util.List;

public class HelloWorld {

    public void writeList() {
        try {
            int a = 10;
            int b = 3;
            int c = a / b;
            InputStream in = new FileInputStream("");
            int ch = in.read();
            in.close();
        } catch (ArithmeticException | IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
