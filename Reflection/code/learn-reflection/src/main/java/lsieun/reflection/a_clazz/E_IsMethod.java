package lsieun.reflection.a_clazz;

import java.util.concurrent.TimeUnit;

public class E_IsMethod {
    private Runnable r = new Runnable() {
        @Override
        public void run() {

        }
    };

    public static void main(String[] args) {
        System.out.println("Is primitive Type: " + int.class.isPrimitive());
        System.out.println("Is Array: " + int[].class.isArray());
        System.out.println("Is Enum: " + TimeUnit.class.isEnum());
        System.out.println("Is Interface: " + Cloneable.class.isInterface());
        System.out.println("Is Annotation: " + Deprecated.class.isAnnotation());
        System.out.println("Is Synthetic: " + int.class.isSynthetic());
    }
}
