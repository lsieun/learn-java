package lsieun.javaagent.handler;

import javassist.CtMethod;
import lsieun.javaagent.util.CodeSegment;

public class InfoHandler extends Handler {
    public InfoHandler(String methodSignature, boolean regex) {
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
        method.insertBefore(codeBefore.getString());
    }
}
