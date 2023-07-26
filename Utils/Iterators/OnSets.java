/*
README: Maybe HashSet is not a data structure suitable for performing iterations since its
strengths lie in other features, for example the contains() method which has O(1) constant average
computational time complexity. Combined with ADS (Addition, Deletion and Searching[being searching
contains()]) operations which are also O(1) on average.

This example is basically the same as OnMaps but dealing with a type of Set named HashSet.

Resources:
[1] https://docs.oracle.com/javase/8/docs/api/java/util/Set.html
[2] https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
*/

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class OnMaps {
	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		set.add("Sasha");
		set.add("Natasha");
		set.add("Ekaterina");
		set.add("Nastya");
		set.add("Irina");

		//Iterating using stream can be used directly. Here is used parallelStream() for parallel access.
		set.parallelStream().forEach(System.out::println);
		System.out.println("---------------------");

		//Iterating using an enhanced for loop.
		for(String element : set) {
			System.out.println(element);
		}
		System.out.println("---------------------");

		//Iterating using Iterator capbilities.
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String value = it.next();
			System.out.println(value);
		}
		System.out.println("---------------------");
	}
}
