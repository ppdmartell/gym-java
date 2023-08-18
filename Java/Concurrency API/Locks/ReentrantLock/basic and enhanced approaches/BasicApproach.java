/*
This program basically increments a variable from 0 - 1000. The program is multi-threaded,
however, there is no synchronization mechanism to access the variable count and modify it.
For this reason the value printed, using my device,  most of the time is 1000 but sometimes
is 994, 997, 996...
This idea and code is based on [1].

Resources;
[1] https://www.youtube.com/watch?v=ddUSe3A9MMg
*/


import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

class BasicApproach {

    private static int count;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        IntStream.range(0, 1000).forEach(i -> executorService.submit(BasicApproach::increment));
        Thread.sleep(3000);
        executorService.shutdown();
        System.out.println(count);
    }

    public static void increment() { count++; }
}
