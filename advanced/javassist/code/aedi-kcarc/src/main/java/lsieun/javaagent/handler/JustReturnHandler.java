package lsieun.javaagent.handler;

import javassist.CtMethod;
import lsieun.javaagent.util.CodeSegment;

public class JustReturnHandler extends Handler {
    public JustReturnHandler(String methodSignature, boolean regex) {
        super(methodSignature, regex);
    }

    @Override
    public void process(CtMethod method) throws Exception {
        CodeSegment codeBefore = new CodeSegment();
        codeBefore.printBlankLine(3);
        codeBefore.printStartMark();
        codeBefore.printMethodInfo();
        codeBefore.printSignature();
        codeBefore.printArguments();
        codeBefore.printEndMark();
        codeBefore.addJustReturn();
        method.insertBefore(codeBefore.getString());
    }
}
