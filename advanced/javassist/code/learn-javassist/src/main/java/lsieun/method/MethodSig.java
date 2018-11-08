package lsieun.method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lsieun.geometry.Point;
import lsieun.geometry.Rectangle;

public class MethodSig {
    public static void main(String[] args) {
        try {
            String fullClassName = "lsieun.geometry.Point";
            String methodName = "move";

            ClassPool pool = ClassPool.getDefault();
            //pool.importPackage("lsieun.geometry");
            CtClass cc = pool.get(fullClassName);
            CtMethod m = cc.getDeclaredMethod(methodName);

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("System.out.println(\"signature: \" + $sig.length);");
            sb.append("    for (int i=0; i<$sig.length; i++) {");
            sb.append("        System.out.println(\"\t\" + $sig[i]);");
            sb.append("    }");
            sb.append("System.out.println(\"return type: \" + $type);"); //void
            sb.append("System.out.println(\"class: \" + $class);"); // class lsieun.geometry.Point
            sb.append("}");
            m.insertBefore(sb.toString());

            //cc.writeFile();
            Class clazz = cc.toClass();
            Point p = new Point();
            System.out.println("Before Move: " + p);
            p.move(3, 5);
            System.out.println("After Move: " + p);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
