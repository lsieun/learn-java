package lsieun.except.java7.multi_catch;

public class A {
    public void before_java7_multi_catch() {
        try {
            String[] array = new String[10];
            String str = array[0].toUpperCase();
            System.out.println(str);
        }
        catch (NullPointerException ex1) {
            ex1.printStackTrace();
        }
        catch (ArrayIndexOutOfBoundsException ex2) {
            ex2.printStackTrace();
        }
    }

    public void java7_multi_catch() {
        try {
            String[] array = new String[10];
            String str = array[0].toUpperCase();
            System.out.println(str);
        }
        catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
}
