package lsieun.format.how_to_use;

import java.util.Formatter;

public class ThreeMethod {
    public static void main(String[] args) {
        // 第一种方式：使用String.format方法（最常用）
        String output = String.format("%s = %d", "joe", 35);
        System.out.println(output);

        // 第二种方式：使用PrintStream的printf或format方法
        // printf方法本质上是调用format方法
        System.out.printf("My name is %s%n", "joe");
        System.out.format("%s = %d%n", "joe", 35);

        // 第三种方式：使用Formatter和StringBuilder相结合
        StringBuilder sb = new StringBuilder();
        Formatter fmt = new Formatter(sb);
        fmt.format("PI = %f%n", Math.PI);
        System.out.print(sb.toString());
    }
}
