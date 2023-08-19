/*
Comparable, unlike Comparator has to be implemented by the class, and the method compareTo() overriden.
The Comparable interface is part of java.lang package so you don't need to import its belonging package.

Resources:
[1] https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html
[2] https://chat.openai.com/c/594e7820-badd-4bad-a709-75ada1cc31c6 (keyword search: "example demonstrating the use of `Comparable`")
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class ComparableExample {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Jack", 20));
        list.add(new Person("Niarmadine", 77));
        list.add(new Person("Alimonde", 11));

        Collections.sort(list);
        System.out.println(list);
    }
}

class Person implements Comparable<Person> {
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

    @Override
    public int compareTo(Person other) {
        return age - other.getAge();
    }
}
