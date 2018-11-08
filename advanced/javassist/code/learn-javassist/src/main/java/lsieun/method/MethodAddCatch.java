package lsieun.method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lsieun.geometry.Point;

public class MethodAddCatch {
    public static void main(String[] args) {
        try {
            String fullClassName = "lsieun.geometry.Point";
            String methodName = "getError";

            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get(fullClassName);
            CtMethod m = cc.getDeclaredMethod(methodName);

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("    System.out.println(\"捕获错误\");");
            sb.append("    System.out.println(ex);");
            //sb.append("    ex.printStackTrace();");
            sb.append("    return;");
            sb.append("}");
            m.addCatch(sb.toString(), pool.get("java.lang.Exception"), "ex");
//            for (CtMethod m : methods) {
//                StringBuilder sb = new StringBuilder();
//                sb.append("{");
//                sb.append("System.out.println(\"signature: \" + $sig.length);");
//                sb.append("    for (int i=0; i<$sig.length; i++) {");
//                sb.append("        System.out.println(\"\t\" + $sig[i]);");
//                sb.append("    }");
//
//                sb.append("}");
//                m.insertBefore(sb.toString());
//            }


            //cc.writeFile();
            Class clazz = cc.toClass();
            Point p = new Point();
            p.getError();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
