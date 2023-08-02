/*
This exercise is about Threads and is based on [1] video.
By the way, a Thread has six possible states[2].

Resources:
[1] https://www.youtube.com/watch?v=7eV4nib3Cm8
[2] https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.State.html
*/

//import java.util.concurrent.atomic.AtomicInteger;

class App2 {
	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();
		Thread t1;
		Thread t2;
		t1 = new Thread(() -> {
			for(int i = 0; i < 10000; i++) counter.increment();
		});

		t2 = new Thread(() -> {
			for(int i = 0; i < 10000; i++) counter.increment();
		});

		t1.start();
		t2.start();

		//This try-catch block of code is the one that doesn't allow the println to be
		//executed before the threads haven't ended the execution. That's why the resulting
		//number was wrong EVEN when the AtomicInteger and synchronized method (and volatile int).
		//You can avoid the try-catch by declaring the main method "throws InterrupedException".
		/*try {
			Thread.sleep(10);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}*/

		/*A more elegant way to wait for the threads to die (finish) is to use join() method on each*/
		t1.join();
		t2.join();

		System.out.println(counter.counter);
	}
}

//In this class, both ways should work for getting 20_000 as final result.
class Counter {
	//AtomicInteger counter = new AtomicInteger(0);  //The volatile for AtomicInteger is not necessary.
	volatile int counter;

	public synchronized void increment() {
		//this.counter.set(counter.get() + 1);
		counter++;
	}
}
