/*
LinkedHashSet is a data structure that allows you to keep the order of insertion
and uniqueness of its elements. This data structure maintains the insertion order,
however you can't use a Comparator to order its elements during insertion. You also
can't do a direct access by index.

If you need to know if an element exists in the LinkedHashSet you can use contains()
method which the computational time complexity should be O(1).
*/

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

class App {
	public static void main(String[] args) {
		Set<Integer> set = new LinkedHashSet<>();
		set.addAll(Arrays.asList(6, 2, 3, 4, 5, 5, 1));
		set.stream().forEach(System.out::println);
	}
}
