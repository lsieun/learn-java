package lsieun.except.unchecked;

public class F_IllegalStateException {
    private static int count = 0;

    public static void main(String[] args) {
        test();
        test();
    }

    public static void test() {
        if (count == 1) {
            throw new IllegalStateException("can only be invoked once");
        }
        System.out.println("invoke test method");
        count++;
    }
}
