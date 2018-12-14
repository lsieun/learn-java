package lsieun.java;

public class JavaVendor {
    public static void main(String[] args) {
        String vendor = System.getProperty("java.vendor");
        String url = System.getProperty("java.vendor.url");
        System.out.println(vendor);
        System.out.println(url);
    }
}
