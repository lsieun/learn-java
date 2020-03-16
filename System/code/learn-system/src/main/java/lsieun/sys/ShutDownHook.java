package lsieun.sys;

public class ShutDownHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Running Shutdown Hook");
            }
        });

        System.out.println("A");
        System.exit(0);
        System.out.println("B");
    }
}
