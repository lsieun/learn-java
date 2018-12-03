package lsieun.javaagent.handler;

import javassist.CtMethod;
import lsieun.javaagent.util.CodeSegment;

public class PingHandler extends Handler {
    public PingHandler(String methodSignatureRegExp) {
        super(methodSignatureRegExp);
    }

    @Override
    public void process(CtMethod method) throws Exception {
        CodeSegment codeBefore = new CodeSegment();
        codeBefore.printBlankLine(2);
        codeBefore.printStartMark();
        codeBefore.printMethodInfo();
//        codeBefore.printSignature();
//        codeBefore.printArguments();
        codeBefore.printEndMark();
        codeBefore.addPingResponse();
        method.insertBefore(codeBefore.getString());

//        CodeSegment codeAfter = new CodeSegment();
//        codeAfter.printReturnValue();
//        codeAfter.printEndMark();
//        method.insertAfter(codeAfter.getString());
        System.out.println("Ping Method All right: " + method.getDeclaringClass().getName() + "#" + method.getName());
    }
}
