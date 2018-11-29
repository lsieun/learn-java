package lsieun.jvm.engine.gc;

public class OutputTest02_Finalize_Exception {
    public static void main(String[] args) throws InterruptedException {
        OutputTest02_Finalize_Exception t = new OutputTest02_Finalize_Exception();

        // making t eligible for garbage collection
        t = null;

        // calling garbage collector
        System.gc();

        // waiting for gc to complete
        Thread.sleep(1000);

        System.out.println("end main");
    }

    @Override
    protected void finalize() {
        System.out.println("finalize method called");
        System.out.println(10/0);
    }
}
