package lsieun.jvm.engine.gc;

public class OutputTest03_Finalize_Count {

    static OutputTest03_Finalize_Count t ;
    static int count =0;

    public static void main(String[] args) throws InterruptedException {
        OutputTest03_Finalize_Count t1 = new OutputTest03_Finalize_Count();

        // making t1 eligible for garbage collection
        t1 = null; // line 12

        // calling garbage collector
        System.gc(); // line 15

        // waiting for gc to complete
        Thread.sleep(1000);
        System.out.println("First GC");
        // making t eligible for garbage collection,
        t = null; // line 21

        // calling garbage collector
        System.gc(); // line 24

        // waiting for gc to complete
        Thread.sleep(1000);
        System.out.println("Second GC");

        System.out.println("finalize method called "+count+" times");

    }

    @Override
    protected void finalize() {
        System.out.println("finalize");
        count++;
        t = this; // line 38
    }
}
