public class ReadStringFromCommondLine {
    public static void main(String[] args) {
        String value = System.getProperty("test.str");
        System.out.println(value);
    }
}
