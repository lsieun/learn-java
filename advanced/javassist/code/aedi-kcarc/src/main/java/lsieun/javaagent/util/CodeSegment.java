package lsieun.javaagent.util;

public class CodeSegment {
    private StringBuilder sb;

    public CodeSegment () {
        this.sb = new StringBuilder();
        this.sb.append("{");
    }

    public void add(String str) {
        this.sb.append(str);
    }

    public void printBlankLine(int n) {
        for(int i=0; i<n; i++) {
            this.sb.append("    System.out.println();");
        }
    }

    public void printMethodInfo() {
        this.sb.append("    System.out.println(\"Method Agent: \" + $class);");
//        this.sb.append("    final StackTraceElement[] ste = Thread.currentThread().getStackTrace();");
//        this.sb.append("    String name = ste[ste.length - 2].getMethodName();");
//        this.sb.append("    System.out.println(\"Method Name: \"name);");

        this.sb.append("    System.out.println(\"Return Type: \" + $type);");
    }

    public void printSignature() {
        this.sb.append("    System.out.println(\"signature: \" + $sig.length);");
        this.sb.append("    for (int i=0; i<$sig.length; i++) {");
        this.sb.append("        System.out.println(\"\t\" + $sig[i]);");
        this.sb.append("    }");
    }

    public void printArguments() {
        sb.append("    System.out.println(\"Args: \" + $args.length);");
        sb.append("    for (int i=0; i<$args.length; i++) {");
        sb.append("        System.out.println(\"\t\" + $args[i]);");
        sb.append("    }");
    }

    public void printReturnValue() {
        this.sb.append("System.out.println(\"Return Value: \" + $_);");
    }

    public void printStackTrace() {
        this.sb.append("Exception ex = new Exception(\"Aegis\");");
        this.sb.append("ex.printStackTrace();");
    }

    public void printStartMark() {
        this.sb.append("    System.out.println(\"====================>>>>>>>>>>>>\");");
    }

    public void printEndMark() {
        this.sb.append("System.out.println(\"<<<<<<<<<======================\");");
    }

    public void addPingResponse() {
        this.sb.append("    PingResponse response = new PingResponse();");
        this.sb.append("    response.setResponseCode(ResponseCode.OK);");
        this.sb.append("    boolean flag = true;");
        this.sb.append("    if (flag) return response;");
    }

    public void addObtainTicketResponse() {
        this.sb.append("    ObtainTicketResponse response = new ObtainTicketResponse();");
        this.sb.append("    long timestamp = System.currentTimeMillis();");
        this.sb.append("    response.setResponseCode(ResponseCode.OK);");

        this.sb.append("    response.setTicketId(\"1\");");
        this.sb.append("    String username = System.getProperty(\"user.name\");");
        this.sb.append("    response.setTicketProperties(\"licensee=\" + username + \"\\tlicenseType=0\");");
        this.sb.append("    response.setMessage(\"DIY ObtainTicketResponse Message\");");

        this.sb.append("    response.setSalt(timestamp);");
        this.sb.append("    response.setConfirmationStamp(String.valueOf(timestamp));");

        this.sb.append("    response.setServerUid(\"DIY_server_uid\");");
        this.sb.append("    response.setServerLease(\"DIY_server_lease\");");
        this.sb.append("    response.setLeaseSignature(\"DIY_lease_signature\");");
        this.sb.append("    response.setSignature(\"DIY_signature\");");

        this.sb.append("    long period = 3600 * 60 * 24 * 365 * 10;");
        this.sb.append("    response.setProlongationPeriod(period);");
        this.sb.append("    response.setValidationPeriod(period);");
        this.sb.append("    response.setValidationDeadlinePeriod(period);");
        this.sb.append("    boolean flag = true;");
        this.sb.append("    if (flag) return response;");
    }

    public void addJustReturn() {
        this.sb.append("    boolean flag = true;");
        this.sb.append("    if (flag) return;");
    }

    public String getString() {
        this.sb.append("}");
        return this.sb.toString();
    }

    @Override
    public String toString() {
        return this.getString();
    }
}
