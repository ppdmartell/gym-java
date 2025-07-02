import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

class Example1 {

    public static void main(String[] args) {
        ExecutorService cachedEx = Executors.newCachedThreadPool();
        cachedEx.execute(Example1::print);
        cachedEx.execute(Example1::print);
        cachedEx.execute(Example1::print);
        cachedEx.execute(Example1::print);

        cachedEx.shutdown();
    }

    private static void print() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " -> " + Thread.currentThread().getName());
        }
    }

}

/*
 * What is Executors.newCachedThreadPool()?

 * It's a thread pool that:

 * Creates new threads as needed

 * Reuses idle threads that were previously created (for up to 60 seconds)

 * Great for lots of short-lived asynchronous tasks
 *
 *
 *
 *
 * You don’t tell the pool how many threads to create.

 * It creates as many threads as needed for the tasks you submit.

 * If it already has idle threads, it will reuse them instead of creating new ones.
 * */
