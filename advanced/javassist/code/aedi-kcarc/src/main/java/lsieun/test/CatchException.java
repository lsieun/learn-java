package lsieun.test;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

import com.jetbrains.a.b.D;

import a.a.la;

/**
 * MD5withRSA
 */
public class CatchException {
    private static final long a = 70660741661789L;
    public static void main(String[] args) {
        Class clazz = com.jetbrains.a.b.X.class;
        getClassStaticField(clazz, "a");
        long a = la.a(2589333057744728765L, -3606335950533014760L, MethodHandles.lookup().lookupClass()).a(129176027577947L);
        System.out.println(a);

        testA();
    }

    public static void testA() {
        String var0 = "";
        String var1 = "";
        long var2 = 48407845508968L;
        var2 ^= a;
        System.out.println(var2); // 119044280128309

        long var5 = var2 ^ 131670687884081L;
        System.out.println(var5); // 30261543940100

        byte[] var8 = var0.getBytes(StandardCharsets.UTF_8);

        String var10000 = D.b();
        System.out.println(var10000); // null
    }

    public static void getClassStaticField(Class clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(null);
            System.out.println(value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
