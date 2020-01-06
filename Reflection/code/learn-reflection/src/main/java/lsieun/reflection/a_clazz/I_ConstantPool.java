package lsieun.reflection.a_clazz;

import sun.reflect.ConstantPool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class I_ConstantPool {
    private static final Method targetMethod;

    static {
        try {
            targetMethod = Class.class.getDeclaredMethod("getConstantPool");
            targetMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Class<?> clazz = I_ConstantPool.class;
        ConstantPool cp = getConstantPool(clazz);
        System.out.println(cp.getSize());
        Class<?> class_at_n = cp.getClassAt(12);
        System.out.println(class_at_n);
    }

    public static ConstantPool getConstantPool(Class<?> clazz) {
        try {
            ConstantPool cp = (ConstantPool) targetMethod.invoke(clazz);
            return cp;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Something Wrong");
    }
}
