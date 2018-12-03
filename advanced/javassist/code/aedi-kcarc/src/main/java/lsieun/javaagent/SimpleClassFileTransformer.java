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

                    String reg_Exception = "^\\(Ljava/lang/String;Ljava/lang/String;J\\[Lcom/jetbrains/\\w+/\\w+/\\w+;\\)V$";
                    handlers.add(new CatchExceptionHandler(reg_Exception));

                    String reg_ObtainTicket = "(Ljava/lang/String;Ljava/lang/String;IIZJ)Lcom/jetbrains/ls/responses/ObtainTicketResponse;";
                    handlers.add(new ObtainTicketHandler(reg_ObtainTicket));

                    String reg_Ping = "(Ljava/lang/String;Ljava/lang/String;J)Lcom/jetbrains/ls/responses/PingResponse;";
                    handlers.add(new PingHandler(reg_Ping));

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

    public static void findMethod(
            String className,
            byte[] classfileBuffer,
            String packageName,
            String methodSignature,
            int mode) throws Exception {
        byte[] byteCode = classfileBuffer;

        if (className == null || "".equals(className)) return;
        String fullyQualifiedClassName = className.replace("/", ".");
        if (!fullyQualifiedClassName.startsWith(packageName)) return;

        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ByteArrayClassPath(fullyQualifiedClassName, byteCode));
        pool.insertClassPath("/home/liusen/workdir/dummy/idea-IU-182.4505.22/lib/idea.jar");
        CtClass cc = pool.makeClass(new ByteArrayInputStream(byteCode));
        CtMethod[] methods = cc.getDeclaredMethods();

        // 打印方法
        for (CtMethod method : methods) {
            String signature = method.getSignature();
            boolean find = false;
            if (mode < 0 && signature.startsWith(methodSignature)) {
                find = true;
            }
            else if (mode > 0 && signature.endsWith(methodSignature)) {
                find = true;
            }
            else if (mode == 0 && signature.equals(methodSignature)){
                find = true;
            }
            else {
                find = false;
            }

            if (find) {
                System.out.println("Find ===>");
                System.out.println("Class: " + fullyQualifiedClassName);
                System.out.println("\t" + method.getName() + ": " + signature);
            }

        }

    }

    public static byte[] catConstructor(
            String className,
            byte[] classfileBuffer) throws Exception {
        byte[] byteCode = classfileBuffer;
        String fullyQualifiedClassName = className.replace("/", ".");

        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ByteArrayClassPath(fullyQualifiedClassName, byteCode));
        CtClass cc = pool.makeClass(new ByteArrayInputStream(byteCode));
        CtConstructor[] constructors = cc.getConstructors();

        // 打印构造器
        System.out.println("Get Constructors: " + constructors.length);
        for (CtConstructor con : constructors) {
            System.out.println("\t" + con.getSignature());
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("    System.out.println(\"Args: \" + $args.length);");
            sb.append("    for (int i=0; i<$args.length; i++) {");
            sb.append("        System.out.println(\"\t\" + $args[i]);");
            sb.append("    }");
            sb.append("}");
            con.insertAfter(sb.toString());
        }
        byteCode = cc.toBytecode();
        System.out.println("Constructor All right: " + fullyQualifiedClassName);

        return byteCode;
    }

    public static byte[] catMethod(
            String className,
            byte[] classfileBuffer,
            String methodName,
            String methodSignature) throws Exception {
        byte[] byteCode = classfileBuffer;
        String fullyQualifiedClassName = className.replace("/", ".");

        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ByteArrayClassPath(fullyQualifiedClassName, byteCode));
        pool.insertClassPath("/home/liusen/workdir/dummy/idea-IU-182.4505.22/lib/idea.jar");
        CtClass cc = pool.makeClass(new ByteArrayInputStream(byteCode));

        CtMethod[] methods = cc.getDeclaredMethods(methodName);

        // 打印方法
        System.out.println("Get Methods: " + methods.length);
        for (CtMethod method : methods) {
            String signature = method.getSignature();
            if (methodSignature != null && !"".equals(methodSignature)) {
                if (!signature.equals(methodSignature)) {
                    continue;
                }
            }
            System.out.println("\t" + signature);
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
            sb.append("}");
            method.insertBefore(sb.toString());
            method.insertAfter("System.out.println(\"Return Value: \" + $_);");
            method.insertAfter("System.out.println(\"<<<<<<<<<======================\");");
        }

        byteCode = cc.toBytecode();
        System.out.println("Method All right: " + fullyQualifiedClassName);

        return byteCode;
    }

    public static byte[] modifyMethod(
            String className,
            byte[] classfileBuffer,
            String methodName,
            String methodSignature) throws Exception {
        byte[] byteCode = classfileBuffer;
        String fullyQualifiedClassName = className.replace("/", ".");

        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ByteArrayClassPath(fullyQualifiedClassName, byteCode));
        //pool.getPool("/home/liusen/workdir/dummy/idea-IU-182.4505.22/lib/idea.jar");
        pool.insertClassPath("/home/liusen/workdir/dummy/idea-IU-182.4892.20/lib/idea.jar");
        CtClass cc = pool.makeClass(new ByteArrayInputStream(byteCode));
        CtMethod[] methods = cc.getDeclaredMethods(methodName);

        // 打印方法
        System.out.println("Get Methods: " + methods.length);
        for (CtMethod m : methods) {
            String signature = m.getSignature();
            if (methodSignature != null && !"".equals(methodSignature)) {
                if (!signature.equals(methodSignature)) {
                    continue;
                }
            }
            System.out.println("\t" + signature);
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

            sb.append("    System.out.println(\"###Start\");");
            sb.append("    Exception ex = new Exception(\"Aegis\");");
            sb.append("    ex.printStackTrace();");
            sb.append("    System.out.println(\"###Stop\");");

            sb.append("    boolean flag = true;");
            sb.append("    if (flag) return;");
            sb.append("}");
            m.insertBefore(sb.toString());
            m.insertAfter("System.out.println(\"Return Value: \" + $_);");
            m.insertAfter("System.out.println(\"<<<<<<<<<======================\");");

//            StringBuilder sb = new StringBuilder();
//            sb.append("{");
//            sb.append("    System.out.println(\"捕获错误\");");
//            sb.append("    ex.printStackTrace();");
//            sb.append("    return;");
//            sb.append("}");
//            method.addCatch(sb.toString(), pool.get("java.lang.Exception"), "ex");
        }

        byteCode = cc.toBytecode();
        System.out.println("Method All right: " + fullyQualifiedClassName);

        return byteCode;
    }

    public static byte[] modifySignatureMethod(
            String className,
            byte[] classfileBuffer) throws Exception {
        byte[] byteCode = classfileBuffer;
        String fullyQualifiedClassName = className.replace("/", ".");

        if (!fullyQualifiedClassName.startsWith("com.jetbrains")) return byteCode;

//        System.out.println("modifySignatureMethod: " + fullyQualifiedClassName);
        String methodName = "verify";
        String methodSignature = "([B)Z";

        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ByteArrayClassPath(fullyQualifiedClassName, byteCode));
        //pool.getPool("/home/liusen/workdir/dummy/idea-IU-182.4505.22/lib/idea.jar");
        pool.insertClassPath("/home/liusen/workdir/dummy/idea-IU-182.4892.20/lib/idea.jar");
        CtClass cc = pool.makeClass(new ByteArrayInputStream(byteCode));
        CtMethod[] methods = cc.getDeclaredMethods(methodName);

        // 打印方法

        boolean find = false;
        List<String> signatureList = new ArrayList<String>();
        for (CtMethod method : methods) {
            String signature = method.getSignature();
            if (!signature.equals(methodSignature)) {
                continue;
            }

            find = true;
            signatureList.add(signature);

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("    System.out.println();");
            sb.append("    System.out.println();");
            sb.append("    System.out.println(\"====================>>>>>>>>>>>>\");");
            sb.append("    System.out.println(\"Method Agent: \" + $class);");
            sb.append("    System.out.println(\"Return Type: \" + $type);");
//
//            sb.append("    System.out.println(\"signature: \" + $sig.length);");
//            sb.append("    for (int i=0; i<$sig.length; i++) {");
//            sb.append("        System.out.println(\"\t\" + $sig[i]);");
//            sb.append("    }");
//
//            sb.append("    System.out.println(\"Args: \" + $args.length);");
//            sb.append("    for (int i=0; i<$args.length; i++) {");
//            sb.append("        System.out.println(\"\t\" + $args[i]);");
//            sb.append("    }");
            sb.append("    boolean flag = true;");
            sb.append("    if (flag) return;");
            sb.append("}");
            method.insertBefore(sb.toString());
            method.insertAfter("System.out.println(\"Return Value: \" + $_);");
            method.insertAfter("System.out.println(\"<<<<<<<<<======================\");");
        }

        if (find) {
            System.out.println("Find Signature: " + cc.getName());
            for (String sig : signatureList) {
                System.out.println("\t" + sig);
            }
            byteCode = cc.toBytecode();
            System.out.println("Method All right: " + fullyQualifiedClassName);
        }



        return byteCode;
    }


}
