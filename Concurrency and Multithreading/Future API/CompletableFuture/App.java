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
*/

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class App {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//Basic example
		CompletableFuture<String> cFuture = new CompletableFuture<>();
		System.out.println(cFuture.getNow("SoccerNow"));  //This basically giving a default value if the result is not ready, and won't consider the CompletableFuture complete.
		cFuture.complete("Soccer");
		System.out.println(cFuture.get());  //This .get() method will (like Future interface) block the execution until getting the result.

		//Executing code asynchronously
	}
}
