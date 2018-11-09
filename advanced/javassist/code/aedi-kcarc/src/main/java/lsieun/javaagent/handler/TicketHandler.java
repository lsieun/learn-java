package lsieun.javaagent.handler;

import java.io.ByteArrayInputStream;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lsieun.javaagent.util.AssistUtil;

public class TicketHandler {
//    public static byte[] obtainTicket(
//            String className,
//            byte[] classfileBuffer) throws Exception {
//        byte[] byteCode = classfileBuffer;
//        String fullyQualifiedClassName = className.replace("/", ".");
//
//
//        //pool.getPool(new ByteArrayClassPath(fullyQualifiedClassName, byteCode));
//
//        //pool.getPool("/home/liusen/workdir/dummy/idea-IU-182.4505.22/lib/idea.jar");
//        //pool.getPool("/home/liusen/workdir/dummy/idea-IU-182.4892.20/lib/idea.jar");
//        // CtClass cc = pool.makeClass(new ByteArrayInputStream(byteCode));
//
//
//        for (CtMethod m : methods) {
//            String signature = m.getSignature();
//            if (signature.endsWith(")Lcom/jetbrains/ls/responses/ObtainTicketResponse;")) {
//                System.out.println(cc.getName() + "#" + m.getName());
//                System.out.println("\t" + signature);
//
//                StringBuilder sb = new StringBuilder();
//                sb.append("{");
//                sb.append("    System.out.println();");
//                sb.append("    System.out.println();");
//                sb.append("    System.out.println(\"====================>>>>>>>>>>>>\");");
//                sb.append("    System.out.println(\"Method Agent: \" + $class);");
//                sb.append("    System.out.println(\"Return Type: \" + $type);");
//
//                sb.append("    System.out.println(\"signature: \" + $sig.length);");
//                sb.append("    for (int i=0; i<$sig.length; i++) {");
//                sb.append("        System.out.println(\"\t\" + $sig[i]);");
//                sb.append("    }");
//
//                sb.append("    System.out.println(\"Args: \" + $args.length);");
//                sb.append("    for (int i=0; i<$args.length; i++) {");
//                sb.append("        System.out.println(\"\t\" + $args[i]);");
//                sb.append("    }");
//
////                sb.append("    Exception ex = new Exception(\"Aegis\");");
////                sb.append("    ex.printStackTrace();");
//
//                sb.append("    ObtainTicketResponse response = new ObtainTicketResponse();");
//                sb.append("    long timestamp = System.currentTimeMillis();");
//                sb.append("    response.setResponseCode(ResponseCode.OK);");
//
//                sb.append("    response.setTicketId(\"1\");");
//                sb.append("    String username = System.getProperty(\"user.name\");");
//                sb.append("    response.setTicketProperties(\"licensee=\" + username + \"\\tlicenseType=0\");");
//                //sb.append("    response.setTicketProperties(\"licensee=liusen\\tlicenseType=0\");");
//                sb.append("    response.setMessage(\"liusen ObtainTicketResponse Message\");");
//
//                sb.append("    response.setSalt(timestamp);");
//                sb.append("    response.setConfirmationStamp(String.valueOf(timestamp));");
//
//                sb.append("    response.setServerUid(\"liusen_server_id\");");
//                sb.append("    response.setServerLease(\"liusen_serverlease\");");
//                sb.append("    response.setLeaseSignature(\"liusen_leasesignature\");");
//                sb.append("    response.setSignature(\"liusen_signature\");");
//
//                sb.append("    long period = 3600 * 60 * 24 * 365 * 10;");
//                sb.append("    response.setProlongationPeriod(period);");
//                sb.append("    response.setValidationPeriod(period);");
//                sb.append("    response.setValidationDeadlinePeriod(period);");
//                sb.append("    boolean flag = true;");
//                sb.append("    if (flag) return response;");
//                sb.append("}");
//                m.insertBefore(sb.toString());
//                m.insertAfter("System.out.println(\"Return Value: \" + $_);");
//                m.insertAfter("System.out.println(\"<<<<<<<<<======================\");");
//                System.out.println("Method All right: " + fullyQualifiedClassName);
//
//            }
//
//            if (signature.endsWith(")Lcom/jetbrains/ls/responses/PingResponse;")) {
//                System.out.println(cc.getName() + "#" + m.getName());
//                System.out.println("\t" + signature);
//
//                StringBuilder sb = new StringBuilder();
//                sb.append("{");
//                sb.append("    System.out.println();");
//                sb.append("    System.out.println();");
//                sb.append("    System.out.println(\"====================>>>>>>>>>>>>\");");
//                sb.append("    System.out.println(\"Method Agent: \" + $class);");
//                sb.append("    System.out.println(\"Return Type: \" + $type);");
//
//                sb.append("    System.out.println(\"signature: \" + $sig.length);");
//                sb.append("    for (int i=0; i<$sig.length; i++) {");
//                sb.append("        System.out.println(\"\t\" + $sig[i]);");
//                sb.append("    }");
//
//                sb.append("    System.out.println(\"Args: \" + $args.length);");
//                sb.append("    for (int i=0; i<$args.length; i++) {");
//                sb.append("        System.out.println(\"\t\" + $args[i]);");
//                sb.append("    }");
//
//                sb.append("    PingResponse response = new PingResponse();");
//                sb.append("    response.setResponseCode(ResponseCode.OK);");
//                sb.append("    return response;");
//
//                sb.append("}");
//                m.insertBefore(sb.toString());
//                m.insertAfter("System.out.println(\"Return Value: \" + $_);");
//                m.insertAfter("System.out.println(\"<<<<<<<<<======================\");");
//                System.out.println("Method All right: " + fullyQualifiedClassName);
//
//
//            }
//
//
//        }
//
//        byteCode = cc.toBytecode();
//
//        return byteCode;
//    }
}
