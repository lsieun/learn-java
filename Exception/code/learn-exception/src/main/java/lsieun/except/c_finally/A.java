package lsieun.except.c_finally;

public class A {
    public static void main(String[] args) {
        try {
            System.out.println("Hello Try");
            System.exit(0);
        }
        finally {
            System.out.println("Hello Finally");
        }
    }
}
