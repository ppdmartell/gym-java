/*
The Executor Framework abstracts away from the developer the creation of a thread pool,
synchronization among threads and other cumbersome tasks that with simple thread management
become complicated to handle and error prone.
The interface Executor is the root interface of the Executor Framework and has only one method
named "void execute (Runnable command)". The ExecutorService extends it and provides more
useful methods to handle multi-threaded tasks. One of the most important methods of the
ExecutorService interface is submit, which is overloaded, can take either a Runnable and a
Callable, this allows to work with Futures.

Determining the amount of threads to create with the factory creation depends on several factors,
an interesting explanation can be seen in [1].

Resources:
[1] https://chat.openai.com/c/d77e13c2-40b0-42ff-8310-eb84fd399ba4 [search phrase: "one-size-fits-all answer"]
[2] https://www.youtube.com/watch?v=sUVJoUp8gBc
*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class App {
	public static void main(String[] args) {

		//Create a single thread executor and print something.
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(() -> { System.out.println("Printing a simple text using ExecutorService."); });
		executorService.shutdown();

		//Create a pool with 100 threads and print something.
		ExecutorService executorService2 = Executors.newFixedThreadPool(100);
		for(int i = 0; i < 100; i++) {
			executorService2.execute(() -> { System.out.println("This pool has 10 threads. Using now thread: " + Thread.currentThread().getName()); });
		}
		executorService2.shutdown();
	}
}
