/*
Let's use locks (ReentrantLock) this time to control the exclusive access to the increment operation.
According gpt in [1],once you have achieved mutual exclusion is redundant to use Atomic types and
volatile (which unlike Atomic types, only guarantees atomicity for read and write but not compound
operations such as count++).

Resources:
[1] https://chat.openai.com/c/d77e13c2-40b0-42ff-8310-eb84fd399ba4 [search phrase: "volatile instead of AtomicInteger"]
[2] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantLock.html
[3] https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html
*/

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

class LockEnhancedApproach {

	private static int count;
	private static Lock lock = new ReentrantLock(true); //True allows fairness (the longest time waiting thread will be selected [2])


	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		IntStream.range(0, 1000).forEach(i -> executorService.submit(LockEnhancedApproach::increment));
		Thread.sleep(1500);
		executorService.shutdown();
		System.out.println(count);
	}

	private static void increment() {
		lock.lock();
		try {
			count++;
		} finally {
			lock.unlock();
		}
	}
}
