package lsieun.sys;

import java.util.Properties;

public class PropertyOp {
    public static void main(String[] args) {
        testOneProperty();
        System.out.println("======================");
        testAllProperties();
    }

    private static void testOneProperty() {
        String value = null;
        value = System.getProperty("foo");
        System.out.println("Before Set: " + value);

        System.setProperty("foo", "bar");
        value = System.getProperty("foo");
        System.out.println("After Set: " + value);

        System.clearProperty("foo");
        value = System.getProperty("foo");
        System.out.println("After Clear: " + value);
    }

    private static void testAllProperties() {
        Properties props = System.getProperties();
        props.list(System.out);
    }
}
