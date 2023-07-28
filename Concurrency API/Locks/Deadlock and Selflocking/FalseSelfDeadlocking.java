/*
I asked gpt [1] about the possibility for a thread to deadlock itself, it said
it is possible and provided the next code. However, when you execute this code
there is no sign of self-deadlocking at all.

Resources:
[1] https://chat.openai.com/c/d77e13c2-40b0-42ff-8310-eb84fd399ba4 [search phrase: "your statement because it is incorrect"]
*/

public class FalseSelfDeadlocking {
    public static void main(String[] args) {
        Object resource1 = new Object();
        Object resource2 = new Object();

        // Single thread scenario
        synchronized (resource1) {
            // Acquiring resource1 lock
			System.out.printf("Acquiring lock on resource1 by %s with id %d\n", Thread.currentThread().getName(), Thread.currentThread().getId());

            // Now, let's try to acquire resource2 lock, but it's not available
            synchronized (resource2) {
                // This block will never be executed because resource2 is locked by another thread
				System.out.printf("Acquiring lock on resource2 by %s with id %d\n", Thread.currentThread().getName(), Thread.currentThread().getId());
            }
        }
    }
}
