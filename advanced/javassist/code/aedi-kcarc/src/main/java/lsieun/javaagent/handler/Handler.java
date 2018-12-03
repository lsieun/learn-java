package lsieun.javaagent.handler;

import javassist.CtMethod;

public abstract class Handler {
    protected String methodSignature;
    protected boolean regex;

    public Handler(String methodSignature, boolean regex) {
        this.methodSignature = methodSignature;
        this.regex = regex;
    }

    public boolean match(CtMethod method) {
        String sig = method.getSignature();
        if (regex) {
            if (sig.matches(this.methodSignature)) {
                return true;
            }
            return false;
        }
        else {
            if (sig.equals(this.methodSignature)) {
                return true;
            }
            return false;
        }
    }

    public abstract void process(CtMethod method) throws Exception;
}
