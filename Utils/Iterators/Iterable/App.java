/*
The Iterable interface represents a collection of objects which can be iterated. It is important
to note that the Iterable interface is not the same as the Iterator one. They are both are related
but different. The way they are related is an Iterable is a source of an Iterator, meaning this
the Iterable interface has a method "Iterator<T> iterator();". So if a class implements the
Iterable interface then it MUST override the method iterator(), thus returning the iterator.

Resources:
[1] https://www.youtube.com/watch?v=zugG_gFrv34
[2] https://chat.openai.com/c/16f99044-4b80-44a9-b2c5-05a1bd71a56a [search phrase: "elements that can be iterated over"]
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class App {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		//Set<String> list = new HashSet<>();  //Thanks to the upcasting, you can uncomment this line and comment the above one with the List, and the code will run without any issue.
		list.add("element1");
		list.add("element2");
		list.add("element3");

		Collection<String> collection = list;
		Iterable<String> iterable = collection;
		/*The author of [1] took the above steps to depict the up-casting and the relationship between
		  the interfaces Iterable > Collection > List*/

		//WAY 1
		for (String element : iterable) {
			System.out.println(element);
		}
		System.out.println("---------------------");

		//WAY 2
		Iterator<String> it = iterable.iterator();   //This is what is meant with "Iterable is a source of an Iterator"
		while (it.hasNext()) {
			it.forEachRemaining(System.out::println); //This literally says: do this same to the rest of the elements. Method forEachRemaining takes a Consumer functional interface (or a lambda to make it more neat).
		}
		System.out.println("---------------------");

		//WAY 3
		iterable.forEach(System.out::println);   //Iterable interface has a forEach() method that also takes a Consumer functional interface.

		//Interesting but even when Collection interface has a stream() method, Iterable has not!
	}
}
