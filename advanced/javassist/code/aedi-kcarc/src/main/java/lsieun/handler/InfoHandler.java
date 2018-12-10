package lsieun.handler;

import javassist.CtMethod;
import lsieun.utils.CodeSegment;

public class InfoHandler extends Handler {
    public InfoHandler(String methodSignature, boolean regex) {
        super(methodSignature, regex);
    }

    @Override
    public void process(CtMethod method) throws Exception {
        CodeSegment codeBefore = new CodeSegment();
        codeBefore.printBlankLine(2);
        codeBefore.printStartMark();
        codeBefore.printMethodInfo(true, true, true);
//        codeBefore.printEndMark();
        method.insertBefore(codeBefore.getString());
        CodeSegment codeAfter = new CodeSegment();
        codeAfter.printReturnValue();
        codeAfter.printEndMark();
        method.insertAfter(codeAfter.toString());
    }
}
