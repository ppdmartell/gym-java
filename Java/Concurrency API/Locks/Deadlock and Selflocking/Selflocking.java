/*
A method can lock itself (NOT DEADLOCK itself, or at least I couldn't find an example). In the
next code, the thread waits for another thread to acquire the lock and notify() or notifyAll()
in order for the first and existing thread to try to acquire the lock again. But since this
never happens, the program execution is paused indefinitely.

Again, self-locking is IS NOT the same as SELF-DEADLOCKING (which requires two threads trying to
acquire resources each other have, a.k.a. circular dependency)

Resources:
[1] https://stackoverflow.com/a/3493492
*/

class Selflocking {

    public void perform() {
        synchronized (this) {
            System.out.println("Lock acquired by thread: " + Thread.currentThread().getName());
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Selflocking sdl = new Selflocking();
        Thread t1;

        t1 = new Thread(() -> { sdl.perform(); } );
        t1.start();
    }
}
