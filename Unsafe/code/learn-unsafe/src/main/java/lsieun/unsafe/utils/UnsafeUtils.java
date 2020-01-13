package lsieun.unsafe.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class UnsafeUtils {
    private static final Unsafe theUnsafe;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            theUnsafe = (Unsafe) f.get(null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public static Unsafe getUnsafe() {
        return theUnsafe;
    }

    public static long sizeOf(Object o) {
        Unsafe u = getUnsafe();
        Set<Field> fields = new HashSet<>();
        Class c = o.getClass();
        while (c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }

        // get offset
        long maxSize = 0;
        for (Field f : fields) {
            long offset = u.objectFieldOffset(f);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }

        // if no field defined, object header will occupy 12 bytes
        if (maxSize == 0) {
            maxSize = 12;
        }

        return ((maxSize / 8) + 1) * 8;   // padding
    }

//    public static long sizeOf(Object object){
//        return getUnsafe().getAddress(
//                normalize(getUnsafe().getInt(object, 4L)) + 12L);
//    }

    private static long normalize(int value) {
        if (value >= 0) return value;
        return (~0L >>> 32) & value;
    }

    public static Object shallowCopy(Object obj) {
        long size = sizeOf(obj);
        long start = toAddress(obj);
        long address = getUnsafe().allocateMemory(size);
        System.out.println("new Address: " + address);
        getUnsafe().copyMemory(start, address, size);
        return fromAddress(address);
    }

    public static long getAddress(Object obj) {
        Object[] array = new Object[]{obj};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        int addressSize = theUnsafe.addressSize();

        long objectAddress;
        switch (addressSize) {
            case 4:
                objectAddress = theUnsafe.getInt(array, baseOffset);
                break;
            case 8:
                objectAddress = theUnsafe.getLong(array, baseOffset);
                break;
            default:
                throw new Error("unsupported address size: " + addressSize);
        }
        return objectAddress;
    }

    public static long toAddress(Object obj) {
        Object[] array = new Object[]{obj};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        return normalize(getUnsafe().getInt(array, baseOffset));
    }

    public static Object fromAddress(long address) {
        Object[] array = new Object[]{null};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        getUnsafe().putLong(array, baseOffset, address);
        return array[0];
    }
}
