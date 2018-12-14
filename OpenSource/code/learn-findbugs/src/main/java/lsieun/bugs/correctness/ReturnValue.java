package lsieun.bugs.correctness;

public class ReturnValue {
    private String name;
    /**
     * Method whose return value should not be ignored
     */
    public void testEqual() {
        String str = "Java";

        str.toUpperCase();

        if (str.equals("JAVA")) {
            System.out.println("Equal");
        }
        else {
            System.out.println("Not");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }

}
