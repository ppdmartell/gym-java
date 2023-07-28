/*
In this example it is used Integer as the key, since using Set<Person> as a key would
require to override the equals() and hashCode() methods, and also turn the objects
immutable, otherwise the hash code of a changing object could lay unexpected retrievals.
This has to be done with a HashMap<Person, Integer> since the object for Person could change,
that's the need for making the object immutable.
Since Comparator is a functional interface, you can also use a lambda expression for the
constructor.

Resources:
[1] https://chat.openai.com/c/594e7820-badd-4bad-a709-75ada1cc31c6 (search term: "the same principles apply")
*/

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

class App {
	public static void main(String[] args) {
		Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {

			/*
			Don't pay attention to this method overriding, the purpose was to somehow use the Integer type without
			following the natural order and avoid having to use a custom class with overriding equals, hashCode and
			turning it into immutable. So that way the Comparator allows elements to be arranged in a weird
			order (following the compare method overriding) while in being inserted.
			(1, -15, 99, -3 -----> 99, 1, -15, 3)
			*/
			@Override
			public int compare(Integer a, Integer b) {
				if(a % 2 == 1 && b % 2 != 0) return -1;
				if(a < 0 && b >= 0) return 1;
				if(a < 0 && a % 2 != 0) return 1;
				return 0;
			}
		});
		set.add(1);
		set.add(-15);
		set.add(99);
		set.add(-3);
		set.stream().forEach(System.out::println);
	}
}
