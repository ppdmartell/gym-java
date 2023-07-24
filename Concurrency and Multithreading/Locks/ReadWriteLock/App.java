/*
The ReadWriteLock interface specifies a type of lock that maintains two locks, one for read
and another for write access, see [1]. The idea behind read/write locks is that it's usually safe
to read mutable variables concurrently as long as nobody is writing into this variable. So the
read lock can be held simultaneously by multiple threads as long as no threads hold the write lock.

In the next example, one thread holds the write lock and performs the write operation, while two
other threads want to read from that variable. NOTICE that UNTIL the write lock is unlocked, the
read locks can't be acquired. One good thing about this ReadWriteLock lock is that multiple threads
can acquire the read lock simultaneously, as long as the write lock is not acquired.

Resources:
[1] https://www.youtube.com/watch?v=ddUSe3A9MMg
*/

import java.util.ArrayList;
import java.util.List;

class App {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		ReadWriteLock lock = ReentrantReadWriteLock();
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		Runnable writeTask = () -> {
			lock.
		};
	}
}
