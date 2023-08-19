/*
An Optional is a container that either has something in it, or doesn't. It might sound simple
but can save you from Null-based exceptions.

IMPORTANT: Optional was created as a returning type, even when you could use it in a different way.
Still, its nature is as a returning type. Sort of telling the developer: "What you are looking for
might not exist." So try not to over use them besides as a returning type from a method.

Resources:
[1] https://www.youtube.com/watch?v=vKVzRbsMnTQ
[2] https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
[3] https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html
*/

import java.util.NoSuchElementException;
import java.util.Optional;

class App {
    public static void main(String[] args) {
        Dog dog = findByName("Fred");
        System.out.println(dog);

        //Initial case with NullPointerException.
        Dog dog2 = findByName("Zul");                 //There is no dog in the database with the name Zul, so the result will be null.
        try {
            System.out.println(dog2.getName());     //This time let's try to print the name only. But since the object is null, we get a NullPointerException.
        } catch (NullPointerException e) {
            System.out.println("No dog with name Zul, null answer.");
        }

        //Using Optional careless with get().
        Optional<Dog> dog3 = findByNameWithOptional("Seraph");
        System.out.println(dog3);                      //This is one way to access what's inside the Optional.
        try {
            System.out.println(dog3.get().getAge());    //However, the next line will throw an execption NoSuchElementException. So working directly with get() could be risky sometimes.
        } catch (NoSuchElementException e) {
            System.out.println("The value inside the Optional is null, that's why the age can't be printed.");
        }

        //Using Optional carefully with isPresent(). But it's kinda the same as using an if statement.
        Optional<Dog> dog4 = findByNameWithOptional("Fred");
        if (dog4.isPresent()) System.out.println(dog4.get().getName());
        else System.out.println("There is no dog with name Fred.");

        //Using orElse() for an optional value if null.
        Optional<Dog> dog5 = findByNameWithOptional("Fredo");   //If the name if Fred, the age will be 7 because Fred exists, if not, will return the 0 from orElse().
        int age = dog5.orElse(new Dog("UNKNOWN", 0)).getAge();
        System.out.printf("Age for Fredo is %d.%n", age);

        //Using orElseGet(). This method receive a Supplier functional interface.
        Optional<Dog> dog6 = findByNameWithOptional("Tim");
        age = dog6.orElseGet(() -> new Dog("UNKNOWN", 0)).getAge();
        System.out.printf("Age for Tim is %d.%n", age);

        //The true power of Optional comes when we chain callbacks.
        Optional<Dog> dog7 = findByNameWithOptional("Lucas");
        age = dog7.map(Dog::getAge).orElse(0); //This chaining with method reference is elegant, neat and efficient.
        System.out.printf("Age for Lucas is %d.%n", age);
    }

    private static Dog findByName(String name) {
        if (name.toLowerCase().equals("fred")) return new Dog("Fred", 7); //This simulates a database search operation.
        return null;
    }

    private static Optional<Dog> findByNameWithOptional(String name) {
        return Optional.ofNullable(findByName(name));  //If there is a chance this database retrieving operation can return a null, you use ofNullable(), if always not-null, you can use of().
    }
}

class Dog {
    private final String name;
    private final int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return "[name=" + name  + ",age=" + age  + "]";
    }
}
