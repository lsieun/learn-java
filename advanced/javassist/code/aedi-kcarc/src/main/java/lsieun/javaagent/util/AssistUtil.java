package lsieun.javaagent.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javassist.ByteArrayClassPath;
import javassist.ClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import lsieun.javaagent.handler.Handler;

public class AssistUtil {
    private static final ClassPool pool;

    static {
        pool = ClassPool.getDefault();

//        try {
//            // FIXME: 这里应该如何解决呢？不能写成固定值，是否可以写成传入值呢？
//            //pool.getPool(new ByteArrayClassPath(fullyQualifiedClassName, byteCode));
//            //pool.getPool("/home/liusen/workdir/dummy/idea-IU-182.4505.22/lib/idea.jar");
//            pool.getPool("/home/liusen/workdir/dummy/idea-IU-182.4892.20/lib/idea.jar");
//            pool.importPackage("com.jetbrains.ls.responses");
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//        }
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

        boolean find = false;
        for (CtMethod m : methods) {
            for (Handler h : handlers) {
                if (h.match(m)) {
                    find = true;
                    h.process(m);
                }
            }
        }

        if (find) {
            cc.writeFile("/home/liusen/workdir/dummy/tmp");
        }

        return cc.toBytecode();
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

}
