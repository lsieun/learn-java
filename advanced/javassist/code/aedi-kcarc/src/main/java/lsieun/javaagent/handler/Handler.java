package lsieun.javaagent.handler;

import javassist.CtMethod;

public abstract class Handler {
    protected String methodSignature;

    public Handler(String methodSignature) {
        this.methodSignature = methodSignature;
    }

    public boolean match(CtMethod method) {
        String sig = method.getSignature();
        if (sig.equals(this.methodSignature)) {
            return true;
        }
        return false;
    }

    public abstract void process(CtMethod method) throws Exception;
}
