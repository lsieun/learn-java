package lsieun.thread;

public class TestJoin {
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        System.out.println("main over");
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread1 start");
        Thread2 t2 = new Thread2();
        t2.start();
        try {
            t2.join();System.out.println("Thread1 inter");
        } catch (InterruptedException e) {
            System.out.println("Thread1 interrupt");
            e.printStackTrace();
        }
        System.out.println("Thread1 stop");
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread2 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Thread2 interrupt");
            e.printStackTrace();
        }
        System.out.println("Thread2 stop");
    }
}
