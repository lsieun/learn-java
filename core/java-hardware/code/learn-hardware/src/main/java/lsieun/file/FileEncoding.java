package lsieun.file;

public class FileEncoding {
    public static void main(String[] args) {
        String encoding = System.getProperty("file.encoding");
        System.out.println(encoding);
    }
}
