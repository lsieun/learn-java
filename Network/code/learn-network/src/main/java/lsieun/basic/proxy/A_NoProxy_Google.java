package lsieun.basic.proxy;

public class A_NoProxy_Google {
    public static void main(String[] args) {
        System.setProperty("javax.net.debug", "all");

        String url = "https://www.google.com";
        HttpUtils.connect(url);
    }
}
