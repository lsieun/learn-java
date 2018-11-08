package lsieun.constructor;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import lsieun.geometry.Point;

public class ConstructorArgs {
    public static void main(String[] args) {
        try {
            String fully_qualified_class_name = "lsieun.geometry.Point";

            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get(fully_qualified_class_name);
            CtConstructor[] constructors = cc.getDeclaredConstructors();

            for (CtConstructor con : constructors) {
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                sb.append("    System.out.println(\"Args Length: \" + $args.length);");
                sb.append("    for (int i=0; i<$args.length; i++) {");
                sb.append("        System.out.println(\"\t\" + $args[i]);");
                sb.append("    }");
                sb.append("}");
                con.insertAfter(sb.toString());
            }

            cc.toClass();

            System.out.println("====================================");
            Point p1 = new Point();
            System.out.println(p1);
            System.out.println("====================================");
            Point p2 = new Point(5, 5);
            System.out.println(p2);
            System.out.println("====================================");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
