package lsieun.constructor;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;

public class ConstructorSignature {
    public static void main(String[] args) {
        try {
            String fully_qualified_class_name = "lsieun.geometry.Point";

            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get(fully_qualified_class_name);
            CtConstructor[] constructors = cc.getDeclaredConstructors();

            System.out.println("Constructors:");
            for (CtConstructor con : constructors) {
                System.out.println("\t" + con.getSignature());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
