/*
Callable is a functional interface that works a bit like Runnable but returning
a value instead. It has only one method and is "V call()", being V the generic type being returned.
This functional interface itself is not of much use, but when you combine it with Future in
concurrency and multi-threading you get a lot of cool stuff that allows your program
to be more fast (if well coded) and flexible.
Since Callable is a functional interface, you can replace it with a lambda expression.

Resources:
[1] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html
*/

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

class App {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();

		//Anonymous class way.
		Callable<String> callable1 = new Callable<>() {
			@Override
			public String call() {
				//You can perform some tasks here and return the value (in this case a String)
				return "JKDF90WIOS3740GY203970G";
			}
		};

		Future<String> future1 = executor.submit(callable1);
		try {
			//Always try to assign the future get to a corresponding value before, then reuse the variable, just in case..
			//String result = future1.get();
			System.out.println("The decoded serial number is: " + future1.get());
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}
}
