package lsieun.clazz;

import javassist.ClassPool;
import javassist.CtClass;

public class NewInterface {
    public static void main(String[] args) throws Exception  {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeInterface("Geometry");
        cc.writeFile();
    }
}
