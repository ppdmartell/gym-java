/*
Adders are alternatives to Atomic classess, when updates are contended across threads, the set
of variables may grow dynamically to reduce contention. They can be used to consecutively add
values to a number[1]. They are under the package "java.util.concurrent.atomic".

Resources:
[1] https://www.youtube.com/watch?v=MlW8nD4t2js
[2] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/LongAdder.html
*/

import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

class App {
	public static void main(String[] args) throws InterruptedException {
		LongAdder adder = new LongAdder();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		IntStream.range(0, 100).forEach(i -> executor.submit(adder::increment));
		Thread.sleep(2_000);
		System.out.printf("The total incremental sum is %d.%n", adder.sumThenReset());
		executor.shutdown();
	}
}
