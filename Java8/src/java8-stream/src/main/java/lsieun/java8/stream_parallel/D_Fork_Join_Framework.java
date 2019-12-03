package lsieun.java8.stream_parallel;

import lsieun.java8.stream_parallel.bean.ForkJoinSumCalculator;

import static lsieun.java8.stream_parallel.bean.ParallelStreams.measurePerformance;

public class D_Fork_Join_Framework {
    public static void main(String[] args) {
        long n = 10000000;
        System.out.println("ForkJoin sum done in: " + measurePerformance(ForkJoinSumCalculator::forkJoinSum, n) + " msecs");
    }

}
