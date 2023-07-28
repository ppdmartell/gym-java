/*
Fail-safe iterators, unlike fail-fast ones, do allow you to add elements while iterating over the
collection of data, although there is a catch: the addition is done on a copy of the collection of
data. This example is based on [1].

Something to note is that the iterator's capabilities depends more on the data structure and not on
the very Iterator interface. The data structure returns the type of iterator, either a fail-fast or
a fail-safe one. In this case, the data structure used is CopyOnWriteArrayList, which is a thread-safe
one, see [2].

Resources:
[1] https://www.youtube.com/watch?v=PILSlTw4ZDc
[2] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CopyOnWriteArrayList.html
*/

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;
import java.util.List;

class FailSafe {
	public static void main(String[] args) {
		String[] servers = {"Tomcat", "Undertow", "Apache", "WebSphere", "JBoss"};
		List<String> list = new CopyOnWriteArrayList<>(servers); //[2]
		Iterator<String> it = list.iterator(); //CopyOnWriteArrayList's iterator() method returns a fail-safe iterator.
		while (it.hasNext()) {
			String element = it.next();
			System.out.println(element);
			if (element.equals("Tomcat")) {
				list.add("Jetty");           //It's interesting that even when we add the element "Jetty", this one is not shown at the end of the list
											 //and this happens, according to [2], because we are working on a copy of the list and not the actual one.
											 //But still we are being able to add an element to the list while iterating through it (fail-safe).
			}
		}
		System.out.println("------------------------------------------------------");
		list.parallelStream().forEach(System.out::println);
	}
}
