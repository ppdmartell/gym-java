/*
A curious case of self-deadlocking with only one thread can be found in [1].
Basically you are telling the current thread to end its execution and
join (to die and release the resources) but this thread doesn't even have
a Runnable's run method to wait for. And curiously, this creates a deadlock,
a self one. Although I am not sure if calling it that way because there no
explicit lock involved.

Resources:
[1] https://www.geeksforgeeks.org/deadlock-in-single-threaded-java-application/
*/

class SelfDeadlocking {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("BEGIN");
		Thread.currentThread().join();
		System.out.println("END");
	}
}
