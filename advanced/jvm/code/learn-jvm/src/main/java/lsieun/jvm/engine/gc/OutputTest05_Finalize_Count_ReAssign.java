package lsieun.jvm.engine.gc;

public class OutputTest05_Finalize_Count_ReAssign {
    public static void main(String [] args) throws InterruptedException {
        OutputTest05_Finalize_Count_ReAssign t1 = new OutputTest05_Finalize_Count_ReAssign();
        OutputTest05_Finalize_Count_ReAssign t2 = m1(t1); // line 6
        OutputTest05_Finalize_Count_ReAssign t3 = new OutputTest05_Finalize_Count_ReAssign();
        t2 = t3; // line 8
        System.gc();
        Thread.sleep(1000);
    }

    static OutputTest05_Finalize_Count_ReAssign m1(OutputTest05_Finalize_Count_ReAssign temp) {
        temp = new OutputTest05_Finalize_Count_ReAssign();
        return temp;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
