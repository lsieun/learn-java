package lsieun.clazz;

import javassist.ClassPool;
import javassist.CtClass;

public class Test01 {
    public static void main(String[] args) {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass ccRectangle = pool.get("lsieun.geometry.Rectangle");
            CtClass ccPoint = pool.get("lsieun.geometry.Point");
            ccRectangle.setSuperclass(ccPoint);
            ccRectangle.writeFile();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
