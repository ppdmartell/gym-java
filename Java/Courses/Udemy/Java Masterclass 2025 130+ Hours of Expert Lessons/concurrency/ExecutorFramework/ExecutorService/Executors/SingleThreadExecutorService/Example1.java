import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Example1 {

    public static void main(String[] args) {
        ExecutorService firstEx = Executors.newSingleThreadExecutor();
        firstEx.execute(Example1::print);
        firstEx.shutdown();

        ExecutorService secondEx =  Executors.newSingleThreadExecutor();
        secondEx.execute(Example1::print);
        secondEx.shutdown();

        ExecutorService thirdEx = Executors.newSingleThreadExecutor();
        thirdEx.execute(Example1::print);
        thirdEx.shutdown();
    }

    private static void print() {
        for(int i = 0; i < 10; i++) {
            System.out.println(i + " -> " + Thread.currentThread().getName());
        }
    }
}
