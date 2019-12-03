# String

```java
public class B_String {
    private static final String str = "P@33w0rd";

    public static void main(String[] args) {
        test_03_print_number();
    }

    public static void test_01_print_int() {
        str.chars().forEach(System.out::println);
    }

    public static void test_02_print_char() {
        str.chars()
                .mapToObj(ch -> Character.valueOf((char) ch))
                .forEach(System.out::println);
    }

    public static void test_03_print_number() {
        str.chars()
                .filter(ch -> Character.isDigit(ch))
                .mapToObj(ch -> Character.valueOf((char) ch))
                .forEach(System.out::println);
    }
}
```
