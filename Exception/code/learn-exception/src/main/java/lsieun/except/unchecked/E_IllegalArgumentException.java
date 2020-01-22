package lsieun.except.unchecked;

public class E_IllegalArgumentException {
    public static void main(String[] args) {
        String str = getString(null);
    }

    public static String getString(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("obj should not be null");
        }
        return obj.toString();
    }
}
