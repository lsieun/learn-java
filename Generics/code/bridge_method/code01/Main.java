import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
        NumbericValue value = new NumbericValue((byte)0);
        value.compareTo(value);
        //value.compareTo("abc");

        int result1 = reflectiveCompareTo(value, value);
        System.out.println("result1 = " + result1);
        int result2 = reflectiveCompareTo(value, "abc");
        System.out.println("result2 = " + result2);
    }

    public static int reflectiveCompareTo(NumbericValue value, Object other) throws Exception {
        Method method = NumbericValue.class.getMethod("compareTo", new Class<?>[] {Object.class});
        return (Integer) method.invoke(value, new Object[] {other});
    }
}
