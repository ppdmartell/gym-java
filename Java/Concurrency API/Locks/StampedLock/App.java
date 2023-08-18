/*
Stamped locks also handles read and write locks[1] like ReentrantReadWriteLock, however the
difference between them is stamped lock's locking method returns a stamp represented by a long
value. This stamp can be used to either release a lock or to check if the lock is still valid.
Check that as well as with ReentrantReadWriteLock, the read tasks can't start until the write
task has already released the lock on the object.

Stamped locks don't implement reentrant characteristics, each call to the lock method returns
a NEW stamp and blocks the thread if no lock is available, even if the same thread already
holds a lock.

Stamped locks provide the ability to convert read lock into a write lock by using the method
tryConvertToWriteLock().

Resources:
[1] https://www.youtube.com/watch?v=ddUSe3A9MMg
*/

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.StampedLock;
import java.util.List;

class App {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        StampedLock lock = new StampedLock();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Runnable writeTask = () -> {
            long stamp = lock.writeLock();
            try {
                System.out.printf("[%s][%d] Adding FC Barcelona to the list and waiting two seconds.%n", Thread.currentThread().getName(), stamp);
                list.add("FC Barcelona");
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlockWrite(stamp);
            }
        };

        Runnable readTask = () -> {
            long stamp = lock.readLock();
            try {
                System.out.printf("[%s][%d] Reading the first element from the list: %s%n", Thread.currentThread().getName(), stamp, list.get(0));
            } finally {
                lock.unlockRead(stamp);
            }
        };

        executorService.submit(writeTask);
        executorService.submit(readTask);
        executorService.submit(readTask);

        executorService.shutdown();
    }
}
