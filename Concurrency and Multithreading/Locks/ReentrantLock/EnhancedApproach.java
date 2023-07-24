/*
There are several approaches to make the access (and incrementing) to the count variable
safe-thread. You could use synchronized blocks (or declare the method that way) along using
Atomic types (in this case AtomicInt), then communicating the threads with wait() and notify().
Another approach would be to use locks and semaphores.
*/


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

class EnhancedApproach {

	//private static int count; //This will work fine, although is recommended to use Atomic types in multi-threaded programs.
	private static AtomicInteger count;

	static {
		count = new AtomicInteger(0);
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		IntStream.range(0, 1000).forEach(i -> executorService.submit(EnhancedApproach::increment));
		Thread.sleep(1000);
		executorService.shutdown();
		System.out.println(count);
	}

	private static synchronized void increment() {
		System.out.println(Thread.currentThread().getId() + ": " + count.incrementAndGet());
	}
}
