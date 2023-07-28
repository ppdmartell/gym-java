/*
An ArrayBlockingQueue[1] is a bounded blocking queue backed by an array. This queue orders elements
FIFO (first-in-first-out). The head of the queue is that element that has been on the queue the
longest time. The tail of the queue is that element that has been on the queue the shortest time.
New elements are inserted at the tail of the queue, and the queue retrieval operations obtain
elements at the head of the queue.

BlockingQueue[2] is an interface and a type of Queue that additionally supports operations that wait
for the queue to become non-empty when retrieving an element, and wait for space to become
available in the queue when storing an element. It has the next implementing classes:

- ArrayBlockingQueue
- DelayQueue
- LinkedBlockingDeque
- LinkedBlockingQueue
- LinkedTransferQueue
- PriorityBlockingQueue
- SynchronousQueue

You can use add() or put() methods to insert elements in a blocking queue, although they work in a
different way. Check [3] for more details.

Resources:
[1] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ArrayBlockingQueue.html
[2] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html
[3] https://chat.openai.com/c/86acd6d2-b3de-493c-8acb-065c9ac93f31 [search phrase: "insert elements into the queue"]
*/

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class App {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
/*
		//Example 1. This will block the execution because trying to put 4 elements in a row in a
		//3-slots ArrayBlockingQueue. Comment this block to see next examples since this one will block.
		queue.put("element1");
		queue.put("element2");
		queue.put("element3");
		queue.put("element4");

		String element1 = queue.take();
		String element2 = queue.take();
		String element3 = queue.take();
		String element4 = queue.take();

		System.out.println(element1);
		System.out.println(element2);
		System.out.println("--------------------------------------");

		//Example 2. This will block the execution because trying to take 3 when only 2 exist currently
		//Comment this block to see next examples since this one will block.
		queue.put("element5");
		queue.put("element6");

		String element5 = queue.take();
		String element6 = queue.take();
		String element7 = queue.take();

		System.out.println(element5);
		System.out.println(element6);
		System.out.println("--------------------------------------");
*/
		//Example 3. This will work. As long as there is capacity to insert and elements to take, and the
		//amount of put-take is balanced, it should work without any issues. Always respecting the capacity
		//Which in this case is three. You could just insert 3 or less elements without taking and it
		//shouldn't block.
		queue.put("element8");
		queue.put("element9");

		String element8 = queue.take();
		String element9 = queue.take();

		queue.put("element10");
		queue.put("element11");

		String element10 = queue.take();
		String element11 = queue.take();


		System.out.println(element8);
		System.out.println(element9);
		System.out.println(element10);
		System.out.println(element11);
		System.out.println("--------------------------------------");
	}
}
