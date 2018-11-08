package lsieun.method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.MethodInfo;
import lsieun.geometry.Point;

public class MethodDollarArgs {
    public static void main(String[] args) {
        try {
            String fullClassName = "lsieun.geometry.Point";
            String methodName = "move";

            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get(fullClassName);
            CtMethod m = cc.getDeclaredMethod(methodName);

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("    for (int i=0; i<$args.length; i++) {");
            sb.append("        System.out.println(i + \": \" + $args[i]);");
            sb.append("    }");
            sb.append("}");
            m.insertBefore(sb.toString());

            Class clazz = cc.toClass();
            Point p = (Point) clazz.newInstance();
            p.move(5, 10);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
