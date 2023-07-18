/*
When you create a TreeMap, if you don't specify the Comparator in the constructor,
the elements will be ordered by following the natural order of the key (K) in TreeMap<K,V>.
However, if you do specify a Comparator, this comparator must be either the same type of the key
or fulfill the condition Comparator<? super K>.

Something interesting: Since the Comparator is a Functional Interface (only one abstract method),
in principle you can replace a lambda expression for it. And that way, you can declare and
instantiate the TreeMap with a lambda expression in the constructor replacing the Comparator's
compare method overriding.

Resources:
[1] https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
[2] https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html

*/

import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

class ComparatorExample {
	public static void main(String[] args) {
		Map<Integer, Person> treeMap = new TreeMap<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return Integer.compare(a, b);
			}
		});
		Person person1 = new Person("Albert", 50);
		Person person2 = new Person("Messi", 36);
		Person person3 = new Person("Jules", 44);

		treeMap.put(person1.getAge(), person1);
		treeMap.put(person2.getAge(), person2);
		treeMap.put(person3.getAge(), person3);
		treeMap.entrySet().stream().forEach(System.out::println);


		//Using a lambda expression for TreeMap's constructor
		Map<Integer, Person> treeMap2 = new TreeMap<>(Integer::compare);

		Person person4 = new Person("Elon", 50);
		Person person5 = new Person("Jeff", 36);
		Person person6 = new Person("Mark", 70);

		treeMap2.put(person4.getAge(), person4);
		treeMap2.put(person5.getAge(), person5);
		treeMap2.put(person6.getAge(), person6);

		treeMap2.entrySet().stream().forEach(System.out::println);

	}
}

class Person {
	private String name;
	private int age;

	public Person() {}
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public int getAge() { return this.age; }
	public void setAge(int age) { this.age = age; }

	@Override
	public String toString() {
		return "[name: " + name + ", age: " + age + "]";
	}
}
