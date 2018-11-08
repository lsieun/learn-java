package lsieun.method;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lsieun.geometry.Rectangle;

public class MethodDollarUnderscore {
    public static void main(String[] args) {
        try {
            String fullClassName = "lsieun.geometry.Rectangle";
            String methodName = "getSquare";

            ClassPool pool = ClassPool.getDefault();
            pool.importPackage("lsieun.geometry");
            CtClass cc = pool.get(fullClassName);
            CtMethod m = cc.getDeclaredMethod(methodName);

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("Rectangle result = new Rectangle(25, 25);");
            sb.append("return result;");
            sb.append("}");
            m.insertBefore(sb.toString());
            m.insertAfter("System.out.println(\"Result: \" + $_);");
            m.insertAfter("System.out.println(\"width: \" + $_.getWidth());");

            //cc.writeFile();
            Class clazz = cc.toClass();
            Rectangle r = Rectangle.getSquare(5);
            int area = r.getArea();
            System.out.println(area);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
