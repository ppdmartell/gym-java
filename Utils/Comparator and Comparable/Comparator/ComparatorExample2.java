/*
README: If you somehow need to order your list based on something (age), then why not using a TreeMap
or TreeSet to insert the elements already ordered?
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class ComparatorExample2 {
	public static void main(String[] args) {
		List<Person> list = new ArrayList<>(Arrays.asList(
									new Person("Lionel", 45),
									new Person("John", 33),
									new Person("Aurelien", 78),
									new Person("Ali", 1)));
		Collections.sort(list, Comparator.comparingInt(Person::getAge));
		list.stream().forEach(System.out::println);
		System.out.println("---------------------------------------------------");
		Collections.sort(list, Comparator.comparingInt(Person::getAge).reversed());
		list.stream().forEach(System.out::println);

	}
}

final class Person {
	private final String name;
	private final int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public int getAge() { return age; }

	@Override
	public String toString() {
		return "[name=" + name  + ",age=" + age  + "]";
	}
}
