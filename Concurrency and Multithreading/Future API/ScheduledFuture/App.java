/*
ScheduledExecutorService allows you to schedule tasks (Runnable or Callable) and set a moment
in future time where you want them to be executed. Unlike with ExecutorService, you have to
use ScheduledFuture instead of the more common Future to receive the result of the task.
Note that if you use a Runnable, your task won't return anything by design, so you need to
use ScheduledFuture<?>.
See [4] for printf use.

Resources:
[1] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html
[2] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledFuture.html
[3] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TimeUnit.html
[4] https://docs.oracle.com/javase/8/docs/api/java/io/PrintStream.html#printf-java.util.Locale-java.lang.String-java.lang.Object...-
*/

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class App {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ScheduledFuture<Integer> future = ses.schedule(new Callable<>() {
			@Override
			public Integer call() {
				//Perform hypothetical actions...
				return 10 + 7;
			}
		}, 5, TimeUnit.SECONDS);
		Thread.sleep(1_500);
		long remainingTimeToExecution = future.getDelay(TimeUnit.MILLISECONDS);
		System.out.printf("The scheduled task will be executed in: %d miliseconds.\n", remainingTimeToExecution);
		System.out.printf("The computation value for the complex algorithm is: %d\n", future.get());
		ses.shutdown();
	}
}
