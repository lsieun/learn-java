package lsieun.handler;

import javassist.CtMethod;
import lsieun.utils.CodeSegment;

public class PingHandler extends Handler {
    public PingHandler(String methodSignature, boolean regex) {
        super(methodSignature, regex);
    }

    @Override
    public void process(CtMethod method) throws Exception {
        CodeSegment codeBefore = new CodeSegment();
        codeBefore.printBlankLine(2);
        codeBefore.printStartMark();
        codeBefore.printMethodInfo();
        codeBefore.printEndMark();
        codeBefore.addPingResponse();
        method.insertBefore(codeBefore.getString());

        System.out.println("Ping Method All right: " + method.getDeclaringClass().getName() + "#" + method.getName());
    }
}
