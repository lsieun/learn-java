package lsieun.except.unchecked;

public class A_ArrayIndexOutOfBoundsException {
    public static void main(String[] args) {
        int[] anArray = new int[3];
        System.out.println(anArray[3]);
    }
}
