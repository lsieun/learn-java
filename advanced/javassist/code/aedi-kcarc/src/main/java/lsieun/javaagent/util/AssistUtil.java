package lsieun.javaagent.util;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import lsieun.javaagent.handler.Handler;

public class AssistUtil {
    private static final ClassPool pool;

    static {
        pool = ClassPool.getDefault();

        try {
            // FIXME: 这里应该如何解决呢？不能写成固定值，是否可以写成传入值呢？
            //pool.insertClassPath(new ByteArrayClassPath(fullyQualifiedClassName, byteCode));
            pool.insertClassPath("/home/liusen/workdir/dummy/idea-IU-182.4505.22/lib/idea.jar");
            //pool.insertClassPath("/home/liusen/workdir/dummy/idea-IU-182.4892.20/lib/idea.jar");
            pool.importPackage("com.jetbrains.ls.responses");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ClassPool getPool() {
        return pool;
    }

    public static CtClass getClass(String fullyQualifiedClassName,byte[] byteCode) throws Exception {
        pool.insertClassPath(new ByteArrayClassPath(fullyQualifiedClassName, byteCode));
        CtClass cc = pool.makeClass(new ByteArrayInputStream(byteCode));
        return cc;
    }

    public static byte[] getBytes(CtClass cc, List<Handler> handlers) throws Exception {
        CtMethod[] methods = cc.getDeclaredMethods();
        for (CtMethod m : methods) {
            for (Handler h : handlers) {
                if (h.match(m)) {
                    h.process(m);
                }
            }
        }

        return cc.toBytecode();
    }

    public static CtMethod[] findMethods(
            CtClass cc,
            String methodName,
            String methodSignature,
            int mode) throws Exception {
        CtMethod[] methods = null;

        if (methodName == null || "".equals(methodName.trim())) {
            methods = cc.getDeclaredMethods();
        }
        else {
            methods = cc.getDeclaredMethods(methodName);
        }

        if (methods == null || methods.length < 1) return null;

        if (methodSignature == null || "".equals(methodSignature.trim())) return methods;

        List<CtMethod> list = new ArrayList<CtMethod>();
        for (CtMethod m : methods) {
            String sig = m.getSignature();
            if (mode == 0 && sig.equals(methodSignature)) {
                list.add(m);
            }
            if (mode == 1 && sig.startsWith(methodSignature)) {
                list.add(m);
            }
            if (mode == 2 && sig.endsWith(methodSignature)) {
                list.add(m);
            }
        }

        if (list == null || list.size() < 1) return null;

        System.out.println("Find Method: " + cc.getName());
        for (CtMethod m : list) {
            System.out.println("\t" + m.getName() + ": " + m.getSignature());
        }
        return list2Array(list);
    }

    public static CtMethod[] findMethods(
            CtClass cc,
            String methodSignatureRegrex) throws Exception {
        CtMethod[] methods = cc.getDeclaredMethods();
        if (methods == null || methods.length < 1) return null;

        List<CtMethod> list = new ArrayList<CtMethod>();
        for (CtMethod m : methods) {
            String sig = m.getSignature();
            if (sig.matches(methodSignatureRegrex)) {
                list.add(m);
            }
        }

        if (list == null || list.size() < 1) return null;

        System.out.println("Find Method: " + cc.getName());
        for (CtMethod m : list) {
            System.out.println("\t" + m.getName() + ": " + m.getSignature());
        }
        return list2Array(list);
    }

    private static CtMethod[] list2Array(List<CtMethod> list) {
        if (list == null || list.size() < 1) return null;
        CtMethod[] arr = new CtMethod[list.size()];
        for (int i=0; i<list.size(); i++) {
            CtMethod m = list.get(i);
            arr[i] = m;
        }
        return arr;
    }

    public static void catMethods(CtMethod[] methods) throws Exception {
        if (methods == null || methods.length < 1) return;

        for (CtMethod m : methods) {

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("    System.out.println();");
            sb.append("    System.out.println();");
            sb.append("    System.out.println(\"====================>>>>>>>>>>>>Cat Method\");");
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
            m.insertBefore(sb.toString());
            m.insertAfter("System.out.println(\"Return Value: \" + $_);");
            m.insertAfter("System.out.println(\"<<<<<<<<<======================\");");

        }
    }

    public static void returnTrue(CtMethod[] methods) throws Exception {
        if (methods == null || methods.length < 1) return;

        for (CtMethod m : methods) {

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("    System.out.println();");
            sb.append("    System.out.println();");
            sb.append("    System.out.println(\"====================>>>>>>>>>>>>returnTrue\");");
            sb.append("    System.out.println(\"Method Agent: \" + $class);");
            sb.append("    System.out.println(\"Return Type: \" + $type);");

            sb.append("    boolean flag = true;");
            sb.append("    if (flag) return true;");
            sb.append("}");
            m.insertBefore(sb.toString());
            m.insertAfter("System.out.println(\"Return Value: \" + $_);");
            m.insertAfter("System.out.println(\"<<<<<<<<<======================\");");

        }
    }

    public static void returnNull(CtMethod[] methods) throws Exception {
        if (methods == null || methods.length < 1) return;

        for (CtMethod m : methods) {

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("    System.out.println();");
            sb.append("    System.out.println();");
            sb.append("    System.out.println(\"====================>>>>>>>>>>>>returnNull\");");
            sb.append("    System.out.println(\"Method Agent: \" + $class);");
            sb.append("    System.out.println(\"Return Type: \" + $type);");

            sb.append("    boolean flag = true;");
            sb.append("    if (flag) return;");
            sb.append("}");
            m.insertBefore(sb.toString());
            m.insertAfter("System.out.println(\"Return Value: \" + $_);");
            m.insertAfter("System.out.println(\"<<<<<<<<<======================\");");

        }
    }

    public static void printStackTrace(CtMethod[] methods) throws Exception {
        if (methods == null || methods.length < 1) return;

        for (CtMethod m : methods) {

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("    System.out.println();");
            sb.append("    System.out.println();");
            sb.append("    System.out.println(\"====================>>>>>>>>>>>>printStackTrace\");");
            sb.append("    System.out.println(\"Method Agent: \" + $class);");
            sb.append("    System.out.println(\"Return Type: \" + $type);");

            sb.append("    System.out.println(\"###Start\");");
            sb.append("    Exception ex = new Exception(\"Aegis\");");
            sb.append("    ex.printStackTrace();");
            sb.append("    System.out.println(\"###Stop\");");

            sb.append("}");
            m.insertBefore(sb.toString());
            m.insertAfter("System.out.println(\"<<<<<<<<<======================\");");

        }
    }

    public static void catchException(CtMethod[] methods) throws Exception {
        if (methods == null || methods.length < 1) return;

        for (CtMethod m : methods) {

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("    System.out.println();");
            sb.append("    System.out.println();");
            sb.append("    System.out.println(\"====================>>>>>>>>>>>>catchException\");");
            sb.append("    System.out.println(\"Method Agent: \" + $class);");
            sb.append("    System.out.println(\"捕获错误\");");
            sb.append("    ex.printStackTrace();");
            sb.append("    System.out.println(\"<<<<<<<<<======================\");");
            sb.append("    return;");

            sb.append("}");

            m.addCatch(sb.toString(), pool.get("java.lang.Exception"), "ex");
        }
    }
}
