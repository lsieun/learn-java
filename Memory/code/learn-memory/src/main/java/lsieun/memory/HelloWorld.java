package lsieun.memory;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
//        int size = 10;
//        List<Integer> list = new ArrayList<Integer>(size);
//        for (int i = 0; i < size; i++) {
//            list.add(i);
//        }
//
//        System.out.println(ClassLayout.parseClass(ArrayList.class).toPrintable());
//        System.out.println(ClassLayout.parseClass(ArrayList.class).toPrintable(list));
//        System.out.println(GraphLayout.parseInstance(list).toPrintable());
//        System.out.println(GraphLayout.parseInstance(list).toFootprint());

        int value = -134168191;
        long a = (~0L >>> 32) & value;
        System.out.println(a);

    }
}
