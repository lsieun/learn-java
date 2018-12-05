package lsieun.javaagent;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import lsieun.javaagent.handler.CatchExceptionHandler;
import lsieun.javaagent.handler.Handler;
import lsieun.javaagent.handler.ObtainTicketHandler;
import lsieun.javaagent.handler.PingHandler;
import lsieun.javaagent.util.AssistUtil;

public class SimpleClassFileTransformer implements ClassFileTransformer {
    public byte[] transform(
            ClassLoader loader,
            String className,
            Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] byteCode = classfileBuffer;

        if (className == null) return byteCode;

        try {
            String fullyQualifiedClassName = className.replace("/", ".");
            if (findSubPakcage(fullyQualifiedClassName, "com.jetbrains")) {

                if (findClassByRegrex(fullyQualifiedClassName, "^com\\.jetbrains\\.\\w+\\.\\w+\\.\\w+$")) {
                    CtClass cc = AssistUtil.getClass(fullyQualifiedClassName, byteCode);

                    List<Handler> handlers = new ArrayList<Handler>();

//                    String reg_Exception = "^\\(Ljava/lang/String;Ljava/lang/String;J\\[Lcom/jetbrains/\\w+/\\w+/\\w+;\\)V$";
//                    handlers.add(new CatchExceptionHandler(reg_Exception, true));

                    String reg_ObtainTicket = "(Ljava/lang/String;Ljava/lang/String;IIZJ)Lcom/jetbrains/ls/responses/ObtainTicketResponse;";
                    handlers.add(new ObtainTicketHandler(reg_ObtainTicket,false));

                    String reg_Ping = "(Ljava/lang/String;Ljava/lang/String;J)Lcom/jetbrains/ls/responses/PingResponse;";
                    handlers.add(new PingHandler(reg_Ping, false));

                    byteCode = AssistUtil.getBytes(cc, handlers);
                }
            }
        }
        catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("Something Wrong!!!");
            ex.printStackTrace();
            System.out.println("###");
            Runtime.getRuntime().exit(1);
        }

        return byteCode;
    }

    public static boolean findSubPakcage(String fullyQualifiedClassName, String packagePrefix) {
        if (fullyQualifiedClassName == null || "".equals(fullyQualifiedClassName)) return false;
        if (packagePrefix == null || "".equals(packagePrefix)) return false;
        if (fullyQualifiedClassName.startsWith(packagePrefix)) return true;
        return false;
    }

    public static boolean findClass(String fullyQualifiedClassName, String fqcn) {
        if (fullyQualifiedClassName == null || "".equals(fullyQualifiedClassName)) return false;
        if (fqcn == null || "".equals(fqcn)) return false;

        if (fullyQualifiedClassName.equals(fqcn)) {
            // 找到类
            // System.out.println("Find Class===>");
            // System.out.println("\tClassName: " + fullyQualifiedClassName);
            // System.out.println("\tFQCN: " + fqcn);
            return true;
        }
        return false;
    }

    public static boolean findClassByRegrex(String fullyQualifiedClassName, String reg) {
        if (fullyQualifiedClassName == null || "".equals(fullyQualifiedClassName)) return false;
        if (reg == null || "".equals(reg)) return false;

        if (fullyQualifiedClassName.matches(reg)) {
            // 找到类
//            System.out.println("Find Class By Regular Expression===>");
//            System.out.println("\tClassName: " + fullyQualifiedClassName);
//            System.out.println("\tReg: " + reg);
            return true;
        }
        return false;
    }



}
