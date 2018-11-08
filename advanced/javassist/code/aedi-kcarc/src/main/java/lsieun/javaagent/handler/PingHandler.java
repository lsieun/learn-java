package lsieun.javaagent.handler;

import javassist.CtMethod;

public class PingHandler extends Handler {
    public PingHandler(String methodSignatureRegExp) {
        super(methodSignatureRegExp);
    }

    @Override
    public void process(CtMethod method) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("    System.out.println();");
        sb.append("    System.out.println();");
        sb.append("    System.out.println(\"====================>>>>>>>>>>>>\");");
        sb.append("    System.out.println(\"Method Agent: \" + $class);");
        sb.append("    System.out.println(\"Return Type: \" + $type);");

        sb.append("    System.out.println(\"signature: \" + $sig.length);");
        sb.append("    for (int i=0; i<$sig.length; i++) {");
        sb.append("        System.out.println(\"\t\" + $sig[i]);");
        sb.append("    }");

        sb.append("    System.out.println(\"Args: \" + $args.length);");
        sb.append("    for (int i=0; i<$args.length; i++) {");
        sb.append("        System.out.println(\"\t\" + $args[i]);");
        sb.append("    }");

        sb.append("    PingResponse response = new PingResponse();");
        sb.append("    response.setResponseCode(ResponseCode.OK);");
        sb.append("    return response;");

        sb.append("}");
        method.insertBefore(sb.toString());
        method.insertAfter("System.out.println(\"Return Value: \" + $_);");
        method.insertAfter("System.out.println(\"<<<<<<<<<======================\");");
        System.out.println("Ping Method All right: " + method.getDeclaringClass().getName() + "#" + method.getName());
    }
}
