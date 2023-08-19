/*
Accumulator classes are a more generalized version than the Adder classes. Instead of performing
only incremental operations, the class Accumulator builds around a functional interface named
LongBinaryOperator (which of course can be a lambda expression as well, or you can use an
anonymous class and override the method applyAsLong). The code example is depicted in [3].

Resources:
[1] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/LongAccumulator.html
[2] https://docs.oracle.com/javase/8/docs/api/java/util/function/LongBinaryOperator.html
[3] https://www.youtube.com/watch?v=MlW8nD4t2js
*/

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

class App {
    public static void main(String[] args) throws InterruptedException {
        LongBinaryOperator op = (x, y) -> x * y * 2;
        LongAccumulator accumulator = new LongAccumulator(op, 1L);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        IntStream.range(1, 11).forEach(i -> executor.submit(() -> accumulator.accumulate(i)));
        Thread.sleep(2_000);
        executor.shutdown();
        System.out.printf("The total accumulation is %d.%n", accumulator.getThenReset());
    }
}
