package lsieun.jvm.memory.exception;

/**
 * VM Args: -Xss2M
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void statckLeakByThread() {
        while(true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM instance = new JavaVMStackOOM();
        instance.statckLeakByThread();
    }
}
