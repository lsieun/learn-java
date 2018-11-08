package lsieun.field;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lsieun.geometry.Point;

public class FieldStatic {
    public static void main(String[] args) {
        try {
            String fullClassName = "lsieun.geometry.Point";
            String methodName = "jump";

            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get(fullClassName);
            CtMethod m = cc.getDeclaredMethod(methodName);

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("    System.out.println(\"secret: \" + lsieun.geometry.Point.secret);");
            sb.append("    System.out.println(\"x: \" + this.x);");
            sb.append("    System.out.println(\"y: \" + this.y);");
            sb.append("}");
            m.insertBefore(sb.toString());

            //cc.writeFile();
            Class clazz = cc.toClass();
            Point p = new Point();
            System.out.println("Before Move: " + p);
            p.jump(5);
            System.out.println("After Move: " + p);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
