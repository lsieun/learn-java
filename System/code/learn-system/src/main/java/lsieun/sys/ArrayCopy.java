package lsieun.sys;

import java.util.Arrays;

public class ArrayCopy {
    public static void main(String[] args) {
        int[] a = {34, 22, 44, 2, 55, 3};
        int[] b = new int[a.length];

        System.arraycopy(a, 0, b, 0, a.length);
        System.out.println(Arrays.toString(b));
    }
}
