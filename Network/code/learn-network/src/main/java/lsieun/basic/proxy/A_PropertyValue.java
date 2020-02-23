package lsieun.basic.proxy;

public class A_PropertyValue {
    public static void main(String[] args) {
        printSystemProperty("http.nonProxyHosts");
    }

    public static void printSystemProperty(String str) {
        System.out.println(String.format("%s: %s", str, System.getProperty(str)));
    }
}
