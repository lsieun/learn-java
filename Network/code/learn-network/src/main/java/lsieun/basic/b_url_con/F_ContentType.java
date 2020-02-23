package lsieun.basic.b_url_con;

import java.net.URLConnection;

public class F_ContentType {
    public static void main(String[] args) {
        printContentType("index.html");
        printContentType("main.js");
        printContentType("main.css");
        printContentType("good_child.mp4");
        printContentType("good_child.pdf");
        printContentType("foo.c");
    }

    public static void printContentType(String filename) {
        String contentType = URLConnection.getFileNameMap().getContentTypeFor(filename);
        System.out.println(String.format("%s: %s", filename, contentType));
    }
}
