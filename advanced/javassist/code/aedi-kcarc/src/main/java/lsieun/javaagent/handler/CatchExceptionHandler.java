package lsieun.javaagent.handler;

import javassist.ClassPool;
import javassist.CtMethod;
import lsieun.javaagent.util.AssistUtil;

public class CatchExceptionHandler extends Handler {
    public CatchExceptionHandler(String methodSignature, boolean regex) {
        super(methodSignature, regex);
    }

    @Override
    public void process(CtMethod method) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("    System.out.println();");
        sb.append("    System.out.println();");
        sb.append("    System.out.println(\"====================>>>>>>>>>>>>catchException\");");
        sb.append("    System.out.println(\"Method Agent: \" + $class);");
        sb.append("    System.out.println(\"Gotcha\");");
        //sb.append("    System.out.println(\"捕获错误\");");
        //sb.append("    ex.printStackTrace();");
        sb.append("    System.out.println(\"<<<<<<<<<======================\");");
        sb.append("    return;");

        sb.append("}");

        ClassPool pool = AssistUtil.getPool();
        method.addCatch(sb.toString(), pool.get("java.lang.Exception"), "ex");
    }
}
