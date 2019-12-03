package lsieun.java8.stream_parallel;

import lsieun.java8.stream_parallel.bean.ParallelStreams;

import static lsieun.java8.stream_parallel.bean.ParallelStreams.measurePerformance;

public class C_Parallel_Not_Correct {
    public static void main(String[] args) {
        long n = 10000000;
        System.out.println("SideEffect sum done in: " + measurePerformance(ParallelStreams::sideEffectSum, n) + " msecs");
        System.out.println("SideEffect parallel sum done in: " + measurePerformance(ParallelStreams::sideEffectParallelSum, n) + " msecs");
    }

}
