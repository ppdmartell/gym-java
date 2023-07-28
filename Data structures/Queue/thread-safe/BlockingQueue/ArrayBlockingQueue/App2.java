/*
This example is based on the second chapter of [1]. Here it's used threads to simulate a
Producer-Consumer scenario where on thread(Producer) produces something (a String in this case),
then puts it into the ArrayBlockingQueue so the Consumer consumes it. This will happen every two
seconds.

README: Producer and Consumer are classes defined HERE in this file. Do not mistake with Consumer
the functional interface.

Resources:
[1] https://www.youtube.com/watch?v=d3xb1Nj88pw
*/

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class App2 {
	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);

		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();
	}
}

class Producer implements Runnable {
	BlockingQueue<String> queue = null;

	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		long time = 0l;
		while (true) {
			time = System.currentTimeMillis();
			try {
				this.queue.put("" + time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sleep(2_000);
		}
	}

	public void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer implements Runnable {
	BlockingQueue<String> queue = null;

	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		String value = null;
		while (true) {
			try {
				value = this.queue.take();
				System.out.printf("Consumed: %s.%n", value);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
