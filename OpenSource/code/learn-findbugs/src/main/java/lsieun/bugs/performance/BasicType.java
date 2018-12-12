package lsieun.bugs.performance;

public class BasicType {
    public void testInteger() {
        Integer num = new Integer(10);
        System.out.println("num: " + num);
    }

    public void testString() {
        String str = new String("abc");
        System.out.println("str: " + str);
    }
}
