package lsieun.java8.stream_parallel;

public class A_Hardware_Processors {
    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors = " + availableProcessors);
    }
}
