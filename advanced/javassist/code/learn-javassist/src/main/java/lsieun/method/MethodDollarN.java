package lsieun.method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lsieun.geometry.Point;

public class MethodDollarN {
    public static void main(String[] args) {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("lsieun.geometry.Point");
            CtMethod m = cc.getDeclaredMethod("move");

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("System.out.println(\"Before: \" + $0);");
            sb.append("System.out.println($1);");
            sb.append("System.out.println($2);");
            sb.append("}");
            m.insertBefore(sb.toString());
            m.insertAfter("System.out.println(\"After: \" + $0);");

            //cc.writeFile("./target/classes");
            Class clazz = cc.toClass();
            Point p = (Point) clazz.newInstance();
            p.move(5, 10);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
