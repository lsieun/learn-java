package lsieun.memory.a_class;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.FieldLayout;
import org.openjdk.jol.vm.VM;

import java.util.Iterator;
import java.util.SortedSet;

public class C_ObjectInfo {
    public static void main(String[] args) {
//        print_class_and_object(Example.class, new Example());
//        print_class_and_object(int[].class, new int[5]);
//        print_class_and_object(Object[].class, new Object[]{"GOOD", Integer.valueOf(10)});
        print_class_and_object(C.class, new C());

//        Object obj = new Object();
//        int hashCode = obj.hashCode();
//        System.out.println(Integer.toBinaryString(hashCode));
//        print_class_and_object(Object.class, obj);
    }

    public static <T> void print_class_and_object(Class<T> clazz, T t) {
        System.out.println(VM.current().addressOf(clazz));
        long address = VM.current().addressOf(t);
        System.out.println("Address: " + address);
        ClassLayout layout = ClassLayout.parseClass(clazz);
        String result = layout.toPrintable(t);
        System.out.println(result);

    }
}
