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
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.List;

class App {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        ReadWriteLock lock = new ReentrantReadWriteLock(true);
        ExecutorService executorService = Executors.newFixedThreadPool(2);  //With two threads, writing task will be executed by one and read task by second, then thread one will do the remaining read task.

        Runnable writeTask = () -> {
            lock.writeLock().lock();
            try {
                list.add("FC Barcelona");
                System.out.printf("[%s] Waiting two seconds.\n", Thread.currentThread().getName());
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        };

        Runnable readTask = () -> {
            lock.readLock().lock();
            try {
                System.out.printf("[%s] First element of the list is: %s\n", Thread.currentThread().getName(), list.get(0));
            } finally {
                lock.readLock().unlock();
            }
        };

        executorService.submit(writeTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.shutdown();
    }
}
