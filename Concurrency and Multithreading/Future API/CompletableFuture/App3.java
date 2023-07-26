/*
This is the third part of CompletableFuture, will deal with thenCombine() and thenAcceptBoth()
methods for allowing two CompletableFutures to run independently and do something after both
are complete[1]. The callback function passed to thenCombine() will be called when both the
futures are complete.

To check the order of parameters in the lambda expression and see who's who see [2].

Resources:
[1] https://www.youtube.com/watch?v=xpjvY45Hbyg
[2] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
*/

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class App3 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		//Example of using thenCombine() method to retrieve two numbers and make an arithmetic operation with them. There an Async version as well (thenCombineAsync())
		//The thenCombine() method returns a new CompletableFuture to keep performing actions.
		CompletableFuture<Double> positiveFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println("Retrieving a positive double number...");
			return 111.2345;
		});

		CompletableFuture<Double> negativeFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println("Retrieving a negative double number...");
			return -999.234234;
		});

		CompletableFuture<Double> combinedFuture = positiveFuture.thenCombineAsync(negativeFuture, (p, n) -> n - p);
		System.out.printf("The result is %.6f%n", combinedFuture.get());
		System.out.println("-----------------------------------------------------------------------------");

		//Example of using thenAcceptBoth() method. This method is basically the same as thenCombine() but basically returns nothing (actually it returnsbut it's a CompletableFuture<Void>).
		//So you use this one when you don't want to keep passing the CompletableFuture "down" to other callback methods in the chain. And simply perform an action.
		CompletableFuture<Double> positiveFuture1 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Retrieving a positive double number...");
			return 333.2345;
		});

		CompletableFuture<Double> negativeFuture1 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Retrieving a negative double number...");
			return -1771.2345;
		});

		positiveFuture1.thenAcceptBothAsync(negativeFuture1, (n, p) -> System.out.printf("The result is %.4f%n", p - n)).get();
		System.out.println("-----------------------------------------------------------------------------");
	}
}
