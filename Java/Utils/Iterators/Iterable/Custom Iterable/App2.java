import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class App2 {
    public static void main(String[] args) {
        IterableCustomImplementation<Person> list = new IterableCustomImplementation<>();  //Iterable<Person> list = new IterableCustomImplementation<>(); won't give you access to method add() since it belongs to the class an not the interface.
        Person p1 = new Person("Lionel", "Messi");
        Person p2 = new Person("Albert", "Einstein");
        Person p3 = new Person("Blaise", "Pascal");
        list.add(p1);
        list.add(p2);
        list.add(p3);

        for (Person p : list) {
            System.out.println(p);
        }
        System.out.println("---------------------------------------");

        Iterator<Person> it = list.iterator();
        while (it.hasNext()) {
            it.forEachRemaining(System.out::println);
        }
        System.out.println("---------------------------------------");

        list.forEach(System.out::println);
        System.out.println("---------------------------------------");

        for (int i = 0; i < list.getList().size(); i++) {
            System.out.println(list.getList().get(i));
        }

    }
}

class IterableCustomImplementation<T> implements Iterable<T> {
    private final List<T> list = new CopyOnWriteArrayList<>();

    public void add(T t) { this.list.add(t); }

    public List<T> getList() {
        return Collections.unmodifiableList(list);
    }

    @Override
    public Iterator<T> iterator() { return list.iterator(); }
}

class Person {
    private final String name;
    private final String lastname;

    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public String getName() { return name; }

    public String getLastname() { return lastname; }

    @Override
    public String toString() {
        return "[name=" + name  + ",lastname=" + lastname  + "]";
    }
}
