/*
In this example,we try to perform a certain action with three threads at the same time.
One thread will increase a counter. The second one will perform some action when the
counter reaches 5, the third when the counter reaches 7; and finally, two of them
will perform an action when the counter reaches 10.
The sole purpose of this example is to depict the use of wait(), notify() and notifyAll()
because makes no sense to use three threads for doing what one can do in a single run.

[WARNING]: There are bad programming practices here, specially ENCAPSULATION. But they are
here for the sake of simplicity and to focus more on the concurrent and multi-threading
part. So this code doesn't follow good programming practices.

[README]: Read the comments at the end of this file. Managing interactions among several
threads without locks or the Executor framework can be an absolute nightmare.
*/

class App {
    public static void main(String[] args) {
        Counter c = new Counter();
        Thread t1 = new Thread(() -> {
            synchronized (c) {
                for(int i = 0; i < 10; i++) {
                    c.increment();
                    System.out.println("[" + Thread.currentThread().getName() + " says]: Incrementing the counter to: " + c.counter);
                    if(c.counter == 5) {
                        System.out.println("[" + Thread.currentThread().getName() + " says]: Arrived to 5. Will notify and wait...");
                        c.notify();
                        try {
                            c.wait();
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if(c.counter == 7) {
                        System.out.println("[" + Thread.currentThread().getName() + " says]: Arrived to 7. Will notify and wait...");
                        c.notify();
                        try {
                            c.wait();
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if(c.counter == 10) {
                        System.out.println("[" + Thread.currentThread().getName() + " says]: Arrived to 10. Notifying the others...");
                        c.notify();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (c) {
                try {
                    c.wait();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

                if(c.counter == 5) {
                    System.out.println("[" + Thread.currentThread().getName() + " says]: The counter arrived to 5. Notifying...");
                    c.notifyAll(); //If you change this notifyAll() to notify() it will lock because there are two threads waiting at this point. And if thread 0 is not resumed, the counter will stop, stoping thread 2 as well.
                    try {
                        c.wait();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("[" + Thread.currentThread().getName() + " says]: The counter should be 10.");
                    c.notify();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            synchronized (c) {
                try {
                    c.wait();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

                if(c.counter == 7) {
                    System.out.println("[" + Thread.currentThread().getName() + " says]: The counter arrived to 7. Notifying...");
                    c.notify();
                    try {
                        c.wait();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("[" + Thread.currentThread().getName() + " says]: The counter should be 10.");
                    c.notify();
                }
            }
        });

        //The order of starting is critical and it depends on your business logic.
        t2.start();
        t3.start();
        t1.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch(InterruptedException e) {
            System.out.println("Problem trying to join the threads.");
        }

        System.out.println("EXIT EXECUTION");
    }
}

class Counter {
    volatile int counter;

    public synchronized void increment() {
        counter++;
    }
}


/*
Managing interactions between several threads without locks or the Executor Framework
can be an absolute a nightmare sometimes. For example, the output for the execution of this
code can vary, sometimes is (a) and sometimes is (b). And the intended one is (b):

(a)
--------------------------------------------------------------------
[Thread-0 says]: Incrementing the counter to: 1
[Thread-0 says]: Incrementing the counter to: 2
[Thread-0 says]: Incrementing the counter to: 3
[Thread-0 says]: Incrementing the counter to: 4
[Thread-0 says]: Incrementing the counter to: 5
[Thread-0 says]: Arrived to 5. Will notify and wait...
[Thread-1 says]: The counter arrived to 5. Notifying...
[Thread-0 says]: Incrementing the counter to: 6
[Thread-0 says]: Incrementing the counter to: 7
[Thread-0 says]: Arrived to 7. Will notify and wait...
[Thread-1 says]: The counter should be 10.
[Thread-0 says]: Incrementing the counter to: 8
[Thread-0 says]: Incrementing the counter to: 9
[Thread-0 says]: Incrementing the counter to: 10
[Thread-0 says]: Arrived to 10. Notifying the others...
EXIT EXECUTION



(b)
--------------------------------------------------------------------
[Thread-0 says]: Incrementing the counter to: 1
[Thread-0 says]: Incrementing the counter to: 2
[Thread-0 says]: Incrementing the counter to: 3
[Thread-0 says]: Incrementing the counter to: 4
[Thread-0 says]: Incrementing the counter to: 5
[Thread-0 says]: Arrived to 5. Will notify and wait...
[Thread-1 says]: The counter arrived to 5. Notifying...
[Thread-0 says]: Incrementing the counter to: 6
[Thread-0 says]: Incrementing the counter to: 7
[Thread-0 says]: Arrived to 7. Will notify and wait...
[Thread-2 says]: The counter arrived to 7. Notifying...
[Thread-0 says]: Incrementing the counter to: 8
[Thread-0 says]: Incrementing the counter to: 9
[Thread-0 says]: Incrementing the counter to: 10
[Thread-0 says]: Arrived to 10. Notifying the others...
[Thread-2 says]: The counter should be 10.
[Thread-1 says]: The counter should be 10.
EXIT EXECUTION


*/
