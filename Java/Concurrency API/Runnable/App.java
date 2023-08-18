/*
The Runnable interface [1] is a functional interface that executes something but
doesn't return any result. Has only one method (abstract) named run() and doesn't
receive any parameter.

Resources:
[1] https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html
*/

class App {
    public static void main(String[] args) throws InterruptedException {

        //Way 1
        Runnable r1 = () -> {
            System.out.println("Executing a println from inside the run method via lambda expression. (Runnable 1)");
        };

        Thread t1 = new Thread(r1);
        t1.start();

        //Way 2
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing a println from inside the run method via an anonymous class's overriding run method. (Runnable 2)");
            }
        };

        //Way 3
        new Thread(() -> { System.out.println("Executing a println with anonymous thread and lambda expression. (Runnable 3)"); }).start();

        Thread t2 = new Thread(r2);
        t2.start();

        t1.join();
        t2.join();

        System.out.println("END OF EXECUTION");
        return;
    }
}
