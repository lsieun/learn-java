package lsieun.advanced.design;

public class FunctionalInterfaceExample {
    public void runMe(final Runnable r) {
        r.run();
    }

    public static void main(String[] args) {
        FunctionalInterfaceExample instance = new FunctionalInterfaceExample();
        instance.runMe(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Class");
            }
        });

        instance.runMe(() -> System.out.println("Lambda expression"));
    }
}
