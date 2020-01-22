package lsieun.except.unchecked;

public class D_ClassCastException {
    public static void main(String[] args) {
        Object o = new Object();
        Integer i = (Integer)o;
    }
}
