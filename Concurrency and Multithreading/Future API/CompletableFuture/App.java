/*
Java Future API was introduced in Java 5, and a Future[3] is a reference to the result of an
asynchronous computation. However, Future interface has several limitations and due to this
CompletableFuture[2] class was introduced in Java 8 to handle them. Some limitations of the
Future interface are[1]:

1- Can't be completed manually.
2- Can't perform other actions until the result of the computation is available. And it doesn't
notify about its completion. It provides a get method which blocks the further execution
until the result is available.
3- Attaching a callback function is not possible.
4- Multiple futures can't be chained together.
5- Multiple futures can't be combined together.
6- There is not an Exception-handling mechanism.

The CompletableFuture class is used for asynchronous programming in Java as well. Which basically
a mean for writing non-blocking code by running a task on a separate thread rather than the
main application thread. This way the main thread won't have to wait for the execution of this
asynchronous task and would be able to execute other tasks in parallel.

Resources:
[1] https://www.youtube.com/watch?v=xpjvY45Hbyg
[2] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
[3] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html
[4] https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
*/

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class App {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//Basic example
		CompletableFuture<String> cFuture = new CompletableFuture<>();
		System.out.println(cFuture.getNow("SoccerNow"));  //This basically giving a default value if the result is not ready, and won't consider the CompletableFuture complete.
		cFuture.complete("Soccer");
		System.out.println(cFuture.get());  //This .get() method will (like Future interface) block the execution until getting the result.
		System.out.println("------------------------------------------------------------------");

		//Executing a Runnable code asynchronously with runAsync() method.
		System.out.printf("Main thread: %s%n", Thread.currentThread().getName());
		CompletableFuture<Void> cFuture2 = CompletableFuture.runAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.printf("Future thread: %s%n", Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		cFuture2.get();
		System.out.println("------------------------------------------------------------------");

		//Executing a Supplier (see [4] for functional interfaces) code Asynchronously with supplyAsync() method.
		System.out.printf("Main thread: %s%n", Thread.currentThread().getName());
		CompletableFuture<String> cFuture3 = CompletableFuture.supplyAsync(() -> {
			try {
    			TimeUnit.SECONDS.sleep(2);
    			return "Future thread: " +  Thread.currentThread().getName();  //See a return (Supplier) instead of a void statement as the previous example.
			} catch (InterruptedException e) {
    			e.printStackTrace();
				return "Error occurred";
			}
		});
		System.out.println(cFuture3.get());
		System.out.println("------------------------------------------------------------------");

		/*
		So far, CompletableFuture is basically behaving as a Future
		and this is not the purpose. Let's harness the true capabilities of
		CompletableFuture with its enhanced features.
		*/

		//Executing chained operations
		CompletableFuture<String> cFuture4 = CompletableFuture.supplyAsync(() -> "Result X...")
														.thenApply(resultX -> resultX + "processed and turned into Result Y...")
														.thenApply(resultY -> resultY + "processed, execution completed!");

		String result = cFuture4.join(); //This can also be done with get() as in previous cFutures.
		System.out.println(result);
		System.out.println("------------------------------------------------------------------");
	}
}
