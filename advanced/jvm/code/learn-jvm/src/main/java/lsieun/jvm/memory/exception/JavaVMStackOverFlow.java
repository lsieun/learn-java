package lsieun.jvm.memory.exception;

/**
 * VM Args: -Xss128k
 */
public class JavaVMStackOverFlow {
    private int statckLength = 1;

    public void stackLeak() {
        statckLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackOverFlow instance = new JavaVMStackOverFlow();
        try {
            instance.stackLeak();
        } catch (Exception e) {
            System.out.println("stack length: " + instance.statckLength);
            e.printStackTrace();
        }
    }
}
