package lsieun.javaagent.handler;

import javassist.CtMethod;

public class JustReturnHandler extends Handler {
    public JustReturnHandler(String methodSignature) {
        super(methodSignature);
    }

    @Override
    public boolean match(CtMethod method) {
        String sig = method.getSignature();
        if (sig.matches(this.methodSignature)) {
            return true;
        }
        return false;
    }

    @Override
    public void process(CtMethod method) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("    System.out.println();");
        sb.append("    System.out.println();");
        sb.append("    System.out.println(\"====================>>>>>>>>>>>>returnNull\");");
        sb.append("    System.out.println(\"Method Agent: \" + $class);");
        //sb.append("    System.out.println(\"Return Type: \" + $type);");

        sb.append("    boolean flag = true;");
        sb.append("    if (flag) return;");
        sb.append("}");
        method.insertBefore(sb.toString());
        method.insertAfter("System.out.println(\"Return Value: \" + $_);");
        method.insertAfter("System.out.println(\"<<<<<<<<<======================\");");
    }
}
