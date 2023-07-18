/*
A thread is  a separate flow of execution within a program. It represents and independent
sequence of instructions that can be SCHEDULED and executed concurrently with OTHER threads.
Like a tentacle of an octopus doing a task while other tasks are doing their tasks; sometimes
two or more tentacles need to access the same resource and this needs to be managed beforehand,
otherwise there could be problems.

---------------------------------------------------------------------------------------------------
According to gpt there are several ways to create a thread:

1. Extending the `Thread` class: You can create a subclass of the `Thread` class and override its
`run()` method to define the code that will be executed in the thread. You can then create an
instance of your subclass and start the thread by calling its `start()` method.

2. Implementing the `Runnable` interface: You can create a class that implements the `Runnable`
interface and implement its `run()` method. This approach allows you to separate the thread's
behavior from the rest of the class. You can then create an instance of your class and pass it
as an argument to the `Thread` constructor, and then start the thread by calling its `start()`
method.

3. Using lambda expressions or method references: In Java 8 and later versions, you can use lambda
expressions or method references to create threads in a more concise way. You can pass a lambda
expression or a method reference directly to the `Thread` constructor or the `Thread` static
`run()` method.

4. Using the `ExecutorService` framework: The `ExecutorService` framework provides a higher-level
abstraction for managing and executing threads. You can create an `ExecutorService` using the
`Executors` class, submit `Runnable` or `Callable` tasks to it, and it will handle the thread
creation and execution for you.

5. Using Java 8+ `CompletableFuture`: The `CompletableFuture` class in Java 8 and later versions
provides a way to perform asynchronous computations and combine multiple asynchronous operations.
You can use `CompletableFuture` to create and manage threads implicitly.

Each approach has its own advantages and use cases, and the choice depends on the specific
requirements of your application.
---------------------------------------------------------------------------------------------------

Something to note that when you call the method start() it will create a new thread, but if you
call the method run(), it will just execute the instruction from the SAME thread [2]. Even when
they might look having similar in functionality, the reality is totally different.



Resources:
[1] https://www.geeksforgeeks.org/multithreading-in-java/
[2] https://chat.openai.com/c/594e7820-badd-4bad-a709-75ada1cc31c6 [search phrase: "Each thread runs independently and concurrently"]
*/


class App {
	public static void main(String[] args) {

		//Way 1 by extendind Thread class
		System.out.println("---------------WAY ONE----------------");
		for(int i = 0; i < 10; i++) {
			WayOne obj = new WayOne();
			obj.start();  //Different behavior if you call obj.run() instead.
		}

		//Way 2 by implementing interface Runnable
		System.out.println("\n---------------WAY TWO----------------");
		for(int i = 0; i < 10; i++) {
			Thread t = new Thread(new WayTwo());
			t.start();
		}

		//Way 3 by using a lambda expression in the Thread class constructor
		System.out.println("\n---------------WAY THREE----------------");
		for(int i = 0; i < 10; i++) {
			new Thread(() -> {
				String way = "[WAY 3]";
				try {
    				System.out.println(way + " Thread id running: " + Thread.currentThread().getId());
				} catch(Exception e) {
    				System.out.println("[ERROR] There was an unknown problem with the thread execution.");
				}
			}).start();
			//new Thread(System.out::println).start();   <----- Method reference, but there is nothing to print.

		//Way 4 by ExecutorService framework.
		}
	}
}

//Way one is by extending the Thread class.
class WayOne extends Thread {
	@Override
	public void run() {
		String way = "[WAY 1]";
		try {
			System.out.println(way + " Thread id running: " + Thread.currentThread().getId());
		} catch(Exception e) {
			System.out.println("[ERROR] There was an unknown problem with the thread execution.");
		}
	}
}

//Way two is by implementing the Runnable interface
class WayTwo implements Runnable {
	@Override
	public void run() {
		String way = "[WAY 2]";
		try {
   			System.out.println(way + " Thread id running: " + Thread.currentThread().getId());
		} catch(Exception e) {
    		System.out.println("[ERROR] There was an unknown problem with the thread execution.");
		}
	}
}
