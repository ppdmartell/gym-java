/*
Semaphores are a signaling mechanism, opposed to locks which are a mechanism to guarantee
mutual exclusion access to a resource for threads execution. Semaphores also maintain a set
of permits. Semaphores are useful in different scenarios when we need to limit the amount of
concurrent access to whole certain parts of the application[2].

Resources:
[1] https://www.baeldung.com/java-binary-semaphore-vs-reentrant-lock
[2] https://www.youtube.com/watch?v=ddUSe3A9MMg
*/

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class App {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable task = () -> {
            boolean permit = false;
            try {
                permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if(permit) {
                    System.out.printf("[%s] Semaphore acquired.%n", Thread.currentThread().getName());
                    Thread.sleep(5_000);
                } else {
                    System.out.printf("[%s] Could not acquire semaphore.%n", Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                if(permit) semaphore.release();
            }
        };

        IntStream.range(1, 11).forEach(i -> executorService.submit(task));
        executorService.shutdown();
    }
}
