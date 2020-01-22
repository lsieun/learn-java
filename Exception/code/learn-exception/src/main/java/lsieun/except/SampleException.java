package lsieun.except;

public class SampleException {
    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.print("Caught!");
        } finally {
            System.out.print("Finally!");
        }
    }
}
