package lsieun.javaagent.handler;

import javassist.ClassPool;
import javassist.CtMethod;
import lsieun.javaagent.util.AssistUtil;

/**
 * FIXME: Not Working
 */
public class CatchExceptionHandler extends Handler {
    private ClassPool pool;
    public CatchExceptionHandler(String methodSignature, boolean regex, ClassPool pool) {
        super(methodSignature, regex);
        this.pool = pool;
    }

    @Override
    public void process(CtMethod method) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("    System.out.println();");
        sb.append("    System.out.println();");
        sb.append("    System.out.println(\"====================>>>>>>>>>>>>catchException\");");
        sb.append("    System.out.println(\"Method Agent: \" + $class);");
//        sb.append("    System.out.println(\"Gotcha\");");
//        sb.append("    System.out.println(\"捕获错误\");");
        sb.append("    ex.printStackTrace();");
        sb.append("    System.out.println(\"<<<<<<<<<======================\");");
        sb.append("    return;");

        sb.append("}");

        ClassPool pool = this.pool;
        method.addCatch(sb.toString(), pool.get("java.lang.Exception"), "ex");
    }
}
