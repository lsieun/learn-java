package lsieun.clazz;


import javassist.ClassPool;
import javassist.CtClass;

public class NewClass {
    public static void main(String[] args) throws Exception  {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("Point");
        cc.writeFile();
    }
}
