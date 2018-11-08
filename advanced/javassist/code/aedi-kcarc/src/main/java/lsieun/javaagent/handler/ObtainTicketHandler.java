package lsieun.javaagent.handler;

import javassist.CtMethod;

public class ObtainTicketHandler extends Handler {

    public ObtainTicketHandler(String methodSignatureRegExp) {
        super(methodSignatureRegExp);
    }

    @Override
    public void process(CtMethod method) throws Exception{
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

        sb.append("    ObtainTicketResponse response = new ObtainTicketResponse();");
        sb.append("    long timestamp = System.currentTimeMillis();");
        sb.append("    response.setResponseCode(ResponseCode.OK);");

        sb.append("    response.setTicketId(\"1\");");
        sb.append("    String username = System.getProperty(\"user.name\");");
        sb.append("    response.setTicketProperties(\"licensee=\" + username + \"\\tlicenseType=0\");");
        sb.append("    response.setMessage(\"liusen ObtainTicketResponse Message\");");

        sb.append("    response.setSalt(timestamp);");
        sb.append("    response.setConfirmationStamp(String.valueOf(timestamp));");

        sb.append("    response.setServerUid(\"liusen_server_id\");");
        sb.append("    response.setServerLease(\"liusen_serverlease\");");
        sb.append("    response.setLeaseSignature(\"liusen_leasesignature\");");
        sb.append("    response.setSignature(\"liusen_signature\");");

        sb.append("    long period = 3600 * 60 * 24 * 365 * 10;");
        sb.append("    response.setProlongationPeriod(period);");
        sb.append("    response.setValidationPeriod(period);");
        sb.append("    response.setValidationDeadlinePeriod(period);");
        sb.append("    boolean flag = true;");
        sb.append("    if (flag) return response;");
        sb.append("}");
        method.insertBefore(sb.toString());
        method.insertAfter("System.out.println(\"Return Value: \" + $_);");
        method.insertAfter("System.out.println(\"<<<<<<<<<======================\");");

        System.out.println("ObtainTicket Method All right: " + method.getDeclaringClass().getName() + "#" + method.getName());
    }
}
