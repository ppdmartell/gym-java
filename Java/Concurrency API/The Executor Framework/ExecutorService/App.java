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
[3] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html#newWorkStealingPool--
[4] https://www.youtube.com/watch?v=Nb85yJ1fPXM
[5] https://www.youtube.com/watch?v=MB_qCXBSgK0
*/

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

        //Use ExecutorService to retrieve a Person object and then show its data with a prinln.
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        Future<Person> future1 = executorService3.submit(() -> { return new Person("Lionel", "Messi"); });
        try {
            System.out.println("Is it Done?: " + future1.isDone());
            Person resultPerson = future1.get();
            System.out.println("The person retrieved is: " + resultPerson);
            System.out.println("Is it Done?: " + future1.isDone());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService3.shutdown();

        //Use ExecutorService and invokeAll() to submit a list of tasks with a pool with as many threads as CPU cores.
        ExecutorService executorService4 = Executors.newWorkStealingPool(); //[3] Creates a work-stealing thread pool using all available processors as its target parallelism level.
        List<Callable<String>> tasks = Arrays.asList(() -> "Task number 1", () -> "Task number 2", () -> "Task number 3");
        try {
            for(Future<String> future : executorService4.invokeAll(tasks)) {
                System.out.println("Task: " + future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService4.shutdown();
        }
    }
}

class Person {
    private String name;
    private String lastname;

    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public String getName() { return name; }
    public String getLastname() { return lastname; }

    @Override
    public String toString() {
        return "[name=" + name + ", lastname=" + lastname + "]";
    }
}
