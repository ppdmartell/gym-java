import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

class Example1 {

    public static void main(String[] args) {
        int count = 2;
        int time = 3;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(count);
        scheduler.schedule(Example1::print, time,TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(() -> {
                        System.out.println("Repeating -> " + Thread.currentThread().getName());
        }, 1, 2, TimeUnit.SECONDS);


        scheduler.schedule(() -> {
                        System.out.println("Shutting down...");
            scheduler.shutdown();
        }, 10, TimeUnit.SECONDS);
    }

    private static void print() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " -> " + Thread.currentThread().getName());
        }
    }

}
