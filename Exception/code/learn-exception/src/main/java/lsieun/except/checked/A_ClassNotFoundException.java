package lsieun.except.checked;

public class A_ClassNotFoundException {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
