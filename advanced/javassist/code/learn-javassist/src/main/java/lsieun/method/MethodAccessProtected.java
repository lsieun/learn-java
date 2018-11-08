package lsieun.method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lsieun.geometry.Point;

public class MethodAccessProtected {
    public static void main(String[] args) {
        try {
            String fullClassName = "lsieun.geometry.Point";
            String methodName = "move";

            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get(fullClassName);
            CtMethod[] methods = cc.getDeclaredMethods(methodName);

            for (CtMethod m : methods) {
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                sb.append("System.out.println(\"signature: \" + $sig.length);");
                sb.append("    for (int i=0; i<$sig.length; i++) {");
                sb.append("        System.out.println(\"\t\" + $sig[i]);");
                sb.append("    }");

                sb.append("}");
                m.insertBefore(sb.toString());
            }


            //cc.writeFile();
            Class clazz = cc.toClass();
            Point p = new Point();
            p.move(3, 5);
            p.jump(4);
            p.reset();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
