/*
There is a good debate on if classes should be designed as immutable by default in [1].
The creator of Java, James Gosling, was asked when should one use immutables and he said: "I
would use an immutable whenever I can."[3]

According to gpt[2], they should and its reasons are:

A: "Yes, it is generally recommended to design classes as immutable by default, and then,
if necessary, introduce mutability for specific fields based on the business needs and requirements
of the application.

Immutable classes have several advantages, including:

1. Thread Safety: Immutable objects can be safely shared between multiple threads without the need
for additional synchronization mechanisms. This can help avoid many concurrency-related bugs and
issues.

2. Simplified Reasoning: Since the state of an immutable object cannot change after it is created,
the behavior of the object is consistent and predictable, making it easier to reason about the code
and understand its behavior.

3. Cacheable: Immutable objects can be safely cached, as their values never change. This can lead
to better performance by reducing the need to recreate objects with the same values.

4. Hashing and Equality: Immutable objects can be used as keys in hash-based data structures, as
their hash code and equality are fixed based on their initial state.

5. Reusability: Immutable objects can be freely shared and reused across different parts of the
code without worrying about unintended side effects.

By defaulting to immutability, you promote good practices in your codebase and create a more robust
and predictable system. However, in some cases, you may find that certain fields need to be mutable
to reflect the changing state of an object. In such situations, you can carefully introduce
mutability while still maintaining the immutability of the overall class.

Here are some strategies for introducing mutability when needed:

1. Limit Mutability: Make only the necessary fields mutable and keep the majority of the class
immutable. This way, you can control the scope of mutability and minimize the impact on other parts
of the code.

2. Defensive Copying: If you need to return a mutable collection or array from an otherwise immutable
object, return a defensive copy to prevent external modifications.

3. Encapsulation: Keep the mutable state well-encapsulated and hidden from external access. Provide
controlled methods to modify the state and ensure proper synchronization if needed.

4. Immutability by Design: When introducing mutability, carefully design the API and behavior of the
mutable fields to avoid unintended side effects and maintain consistency.

Overall, the key is to strike a balance between immutability and mutability based on the specific
requirements of your application, considering factors such as thread safety, simplicity, and data
integrity."

The accepted answer in [1] states that most classes should be immutable unless they have "IDENTITY",
for example, let's say you have a car and need to fill its tank with gas. There is no reason for
creating a new car with some more gas in. So it is said the car class has identity.
Instead, this gasAmount field, should be made mutable.
Another reason that should stop you from create a class immutable (even if it doesn't have identity)
is when the creation of an object of this class would very be costly in resource-consumption terns.
So creating a very costly object every time wouldn't be good.


Resources:
[1] https://softwareengineering.stackexchange.com/questions/151733/if-immutable-objects-are-good-why-do-people-keep-creating-mutable-objects
[2] https://chat.openai.com/c/d77e13c2-40b0-42ff-8310-eb84fd399ba4 [search phrase: "immutable by default"]
[3] https://www.artima.com/articles/james-gosling-on-java-may-2001
[4] https://chat.openai.com/c/d77e13c2-40b0-42ff-8310-eb84fd399ba4 [search phrase: "immutable builder pattern"]
[5] http://www.javapractices.com/topic/TopicAction.do?Id=15
[6] https://chat.openai.com/c/d77e13c2-40b0-42ff-8310-eb84fd399ba4 [search phrase: "as long as immutability is"]
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class App {
	public static void main(String[] args) {
		List<String> hobbies = new ArrayList<>(Arrays.asList("fishing", "soccer", "movies"));
		Person person = new Person("Dan", "Timor", hobbies);

		List<String> list = person.getHobbies();
		try {
			list.add("golf");
		} catch (UnsupportedOperationException e) {
			System.out.printf("Remember, that method returns an immutable list. Trying to modify that list leads to: %s%n", e.getClass().getSimpleName());
		}
	}
}

final class Person {

	private final String name;
	private final String lastname;
	private final List<String> hobbies;

	public Person (String name, String lastname, List<String> hobbies) {
		this.name = name;
		this.lastname = lastname;
		this.hobbies = new ArrayList<>(hobbies);   //Defensive copy to ensure the new list is immutable because only the class "knows" the location in memory, and returns a new one whose location in memory can be altered without altering the "real" hobbies list.
												   //Don't use Collections.unmodifiableList(hobbies) here!!! Because changes to the existing hobbies (parameter) will be reflected on the class's hobbies.
	}

	public String getName() { return name; }

	public String getLastname() { return lastname; }

	public List<String> getHobbies() {
		//return new ArrayList<>(hobbies);  //This achieves only partial immutability the correct and fully-immutable way[6] is the next line.
		return Collections.unmodifiableList(hobbies);
	}

}
