package lsieun.jvm.engine.gc;

public class OutputTest04_Finalize_Count_Method {
    public static void main(String[] args) throws InterruptedException {
        // How many objects are eligible for
        // garbage collection after this line?
        m1();  // Line 7
        System.gc();
        Thread.sleep(1000);
    }

    static void m1()
    {
        OutputTest04_Finalize_Count_Method t1 = new OutputTest04_Finalize_Count_Method();
        OutputTest04_Finalize_Count_Method t2 = new OutputTest04_Finalize_Count_Method();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
