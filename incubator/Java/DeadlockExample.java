public class DeadlockExample {

    private final Object lock = new Object();

    public synchronized void methodA() {
        System.out.println("Thread " + Thread.currentThread().getId() + " is inside methodA");
        methodB(); // This call will try to acquire the lock again
    }

    public synchronized void methodB() {
        System.out.println("Thread " + Thread.currentThread().getId() + " is inside methodB");
        // This method is intentionally left empty for demonstration purposes.
    }

    public static void main(String[] args) {
        DeadlockExample example = new DeadlockExample();

        Thread thread1 = new Thread(() -> example.methodA());
        Thread thread2 = new Thread(() -> example.methodA());

        thread1.start();
        thread2.start();
    }
}
