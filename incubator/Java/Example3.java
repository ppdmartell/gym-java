/*
About type parameter and generic methods.

Type parameter: Is specified in method almost the same way is specified in class. It belongs
to Generics.
For example: "class CustomLinkedList<>" for class and "public static <T> void handleList(List<T> b)"
The method doesn't return anything by the way, is void. However, if you don't specidy the
type parameter the compiler will complain.

Generic method: Is like a generic class but a method
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Example3 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>(Arrays.asList("Al", "Fred", "Simbad"));
		List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		handleList(list);
		handleList(list2);
	}

	static <T> void handleList(List<T> list) {
		System.out.println("The amount of elements of this list is: " + list.size());
	}
}
