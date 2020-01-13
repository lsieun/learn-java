package lsieun.except;

import java.io.FileNotFoundException;

public class HelloWorld {
    public static int get() {
        int a = 10;
        int b = 0;
        try {
            int c = a / b;
            return c;
        }
        catch (ArithmeticException ex) {
            return 400;
        }
        finally {
            return 500;
        }
    }

    public static void main(String[] args) {
        int result = get();
        System.out.println(result);
    }
}
