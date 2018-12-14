package lsieun.hardware;

public class Processors {
    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println(availableProcessors);
    }
}
