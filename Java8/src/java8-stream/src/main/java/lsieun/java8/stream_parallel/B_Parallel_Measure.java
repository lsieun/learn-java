package lsieun.java8.stream_parallel;

import lsieun.java8.stream_parallel.bean.ParallelStreams;

import static lsieun.java8.stream_parallel.bean.ParallelStreams.measurePerformance;

public class B_Parallel_Measure {
    public static void main(String[] args) {
        long n = 10000000;
        System.out.println("Iterative sum done in: " + measurePerformance(ParallelStreams::iterativeSum, n) + " msecs");
        System.out.println("Sequential sum done in: " + measurePerformance(ParallelStreams::sequentialSum, n) + " msecs");
        System.out.println("Parallel sum done in: " + measurePerformance(ParallelStreams::parallelSum, n) + " msecs");
        System.out.println("Ranged sum done in: " + measurePerformance(ParallelStreams::rangedSum, n) + " msecs");
        System.out.println("Parallel range sum done in: " + measurePerformance(ParallelStreams::parallelRangedSum, n) + " msecs");
    }
}
