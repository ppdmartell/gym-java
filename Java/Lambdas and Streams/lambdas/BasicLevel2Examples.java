/*
Let's now advance a bit more and using functional interfaces as parameters to methods. Mostly custom ones.

Resources:
[1] https://www.youtube.com/watch?v=MqsCdbMQjWc&list=PLqq-6Pq4lTTa9YGfyhyW2CqdtW9RtY-I3&index=14&pp=iAQB
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class BasicLevel2Examples {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Lionel", "Messi"));
        list.add(new Person("Albert", "Einstein"));
        list.add(new Person("Isaac", "Newton"));

        //Let's order the list by length of the lastname, the bigger the length the upper. Using Comparator interface:
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person a, Person b) {
                return a.getLastname().length() == b.getLastname().length() ? 0 : a.getLastname().length() > b.getLastname().length() ? -1 : 1;
            }
        });
        list.stream().forEach(System.out::println);

        //Let's order the list by length of the lastname, the bigger the length the upper. Using a CUSTOM functional interface:
        List<Person> list2 = new ArrayList<>();
        list2.add(new Person("Isacc", "Asimov"));
        list2.add(new Person("Antoine", "Exupery"));
        list2.add(new Person("Dan", "Brown"));

        //Order the list based on the length of the last names.
        CustomComparer<Person> comparer = (a, b) -> Integer.compare(b.getLastname().length(), a.getLastname().length());

        Collections.sort(list2, comparer);
        list2.stream().forEach(System.out::println);

        //Print only the names that start with A in list
        Condition condition = p -> p.getName().startsWith("A");
        printConditionally(list, condition);

        //Print only the names that start with A in list2
        printConditionally(list2, condition);
    }

    static void printConditionally(List<Person> list, Condition condition) {
        list.stream().filter(p -> condition.test(p)).forEach(System.out::println);
    }
}

@FunctionalInterface
interface CustomComparer<T> extends Comparator<T> {
    int compare(T a, T b);
}

@FunctionalInterface
interface Condition {
    boolean test(Person p);
}

class Person {
    private String name;
    private String lastname;

    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    @Override
    public String toString() {
        return "[name: " + name + ", lastname: " + lastname + "]";
    }
}
