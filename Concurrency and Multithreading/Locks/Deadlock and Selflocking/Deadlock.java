/*
A deadlock is when two threads are waiting for each other to release locks they have respectively
to be acquired by each other. But they won't unlock until the other do the same. This is call a
circular dependency:

tx has lock on res1 and want to acquire lock on res2.
ty has lock on res2 and want to acquire lock on res1.

The next code will, after showing the name of the two threads, cause the application to become unresponsive.

Resources:
[1] https://chat.openai.com/c/594e7820-badd-4bad-a709-75ada1cc31c6 [search phrase: "create a circular dependency"]
[2] https://www.youtube.com/watch?v=B4IVu-2hCos
*/

class Deadlock {
	Object res1 = new Object();		//A lock is basically an access to an object. Object class has methods for concurrency and multi-threading by default such as wait(), notify() and notifyAll().
	Object res2 = new Object();

	public void methodA() {
		synchronized (res1) {
			System.out.println("Executing method A by thread: " + Thread.currentThread().getName());
			methodB();
		}
	}

	public synchronized void methodB() {
		synchronized (res2) {
			System.out.println("Executing method B by thread: " + Thread.currentThread().getName());
			methodC();
		}
	}

	public synchronized void methodC() {
		synchronized (res1) {
			System.out.println("Executing method C by thread: " + Thread.currentThread().getName());
			methodA();
		}
	}

	public static void main(String[] args) {
		Deadlock deadlock = new Deadlock();
		new Thread(() -> { deadlock.methodA(); }).start();
		new Thread(() -> { deadlock.methodB(); }).start();
	}
}
