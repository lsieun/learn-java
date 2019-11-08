package lsieun.java8.functional_thinking;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Demo {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        int[] newArray = Arrays.copyOf(array, array.length);
        System.out.println(Arrays.toString(newArray));

//        double[] array = new double[5];
//        //Arrays.parallelSetAll(array, i -> (i % 2 == 0) ? i + 3 : i + 6);
//        Arrays.parallelSetAll(array, i -> i);
//        System.out.println(Arrays.toString(array));
//
//        double[] sums = Arrays.copyOf(array, array.length);
//        Arrays.parallelPrefix(sums, Double::sum);
//        System.out.println(Arrays.toString(sums));
//
//        double[] averages = simpleMovingAverage(array, 3);
//        System.out.println(Arrays.toString(averages));
    }

    public static double[] simpleMovingAverage(double[] values, int n) {
        double[] sums = Arrays.copyOf(values, values.length);
        Arrays.parallelPrefix(sums, Double::sum);
        int start = n - 1;
        return IntStream.range(start, sums.length)
                .mapToDouble(i -> {
                    double prefix = i == start ? 0 : sums[i - n];
                    return (sums[i] - prefix) / n;
                })
                .toArray();
    }
}
