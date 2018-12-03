package lsieun.javaagent.handler;

import javassist.CtMethod;
import lsieun.javaagent.util.CodeSegment;

public class ObtainTicketHandler extends Handler {

    public ObtainTicketHandler(String methodSignature, boolean regex) {
        super(methodSignature, regex);
    }

    @Override
    public void process(CtMethod method) throws Exception{
        CodeSegment codeBefore = new CodeSegment();
        codeBefore.printBlankLine(2);
        codeBefore.printStartMark();
        codeBefore.printMethodInfo();
        codeBefore.printSignature();
        codeBefore.printArguments();
        codeBefore.addObtainTicketResponse();
        method.insertBefore(codeBefore.getString());

        CodeSegment codeAfter = new CodeSegment();
        codeAfter.printReturnValue();
        codeAfter.printEndMark();
        method.insertAfter(codeAfter.getString());

        System.out.println("ObtainTicket Method All right: " + method.getDeclaringClass().getName() + "#" + method.getName());
    }
}
