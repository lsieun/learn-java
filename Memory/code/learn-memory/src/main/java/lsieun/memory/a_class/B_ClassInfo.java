package lsieun.memory.a_class;

import org.openjdk.jol.info.ClassLayout;

public class B_ClassInfo {
    public static void main(String[] args) {
        print_class(Object[].class);
        print_class(int[].class);
        print_class(Example.class);
    }

    public static void print_class(Class<?> clazz) {
        ClassLayout layout = ClassLayout.parseClass(clazz);
        System.out.println(layout.toPrintable());
    }
}
