package lsieun.except.unchecked;

public class B_NullPointerException {
    public static void main(String[] args) {
        String[] strs = new String[3];
        System.out.println(strs[0].length());
    }
}
