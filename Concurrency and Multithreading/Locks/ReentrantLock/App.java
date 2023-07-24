/*

Resources:
[1] https://www.youtube.com/watch?v=ddUSe3A9MMg

*/

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

class App {
	public static void main(String[] args) throws InterruptedException {
		ReentrantLock lock = new ReentrantLock(true);

		Executors.newSingleThreadExecutor().submit(() -> {
			lock.lock();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally {
				lock.unlock();
			}
		});

		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
			System.out.println("--------------------------------------------------------------");
			System.out.println("Lock is acquired and locked?: " + lock.isLocked());  //Interesting, this method won't be available if Lock lock = new ReentrantLock();. Probably because it is part of the ReentrantLock class (static, a class method, not an instance method)
			System.out.println("Is it held by this thread?: " + lock.isHeldByCurrentThread());
			boolean locked = lock.tryLock();
			System.out.println("Were we able to acquire the lock?: " + lock.tryLock());
		}, 0, 1, TimeUnit.SECONDS);
	}
}
