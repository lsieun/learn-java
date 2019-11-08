package lsieun.java8.stream_parallel;

import java.util.function.Function;

public class B_Parallel_Measure {
    public static long measureSumPerformance(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i=0; i< 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1000000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    public static void main(String[] args) {
        System.out.println("Sequential sum done in: " + measureSumPerformance(ParallelStreams::sequentialSum, 10000000) + "msecs");
        System.out.println("Iterative sum done in: " + measureSumPerformance(ParallelStreams::iterativeSum, 10000000) + "msecs");
        System.out.println("Parallel sum done in: " + measureSumPerformance(ParallelStreams::parallelSum, 10000000) + "msecs");
        System.out.println("Ranged sum done in: " + measureSumPerformance(ParallelStreams::rangedSum, 10000000) + "msecs");
        System.out.println("Parallel range sum done in: " + measureSumPerformance(ParallelStreams::parallelRangedSum, 10000000) + "msecs");
    }
}
