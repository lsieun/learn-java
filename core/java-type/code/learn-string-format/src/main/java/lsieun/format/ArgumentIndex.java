package lsieun.format;

public class ArgumentIndex {
    public static void main(String[] args) {
        String value = String.format("%2$s", 32, "Hello"); // Hello
        System.out.println(value);

        String name = "Tom";
        int age = 12;
        String intro = String.format("My Name is %2$s, I'm %1$d years old, My Email is %2$s@gmail.com", age, name);
        System.out.println(intro);
    }
}
