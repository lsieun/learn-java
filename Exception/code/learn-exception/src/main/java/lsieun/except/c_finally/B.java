package lsieun.except.c_finally;

public class B {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Hello Try");
                    while (true) {
                        System.out.println("Hello Try-while");
                    }
                } finally {
                    System.out.println("Hello Finally");
                }
            }
        };
        Thread thread = new Thread(r);
        thread.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
