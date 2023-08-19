import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class App {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>(Arrays.asList(
                                        new Person("Lionel", "Messi"),
                                        new Person("Frenkie", "De Jong"),
                                        new Person("Carles", "Pujol"),
                                        new Person("Ronald", "Araujo")));

        CustomListIterator<Person> it = new CustomListIterator<>(list);  //Interesting, I was using "new CustomListIterator(list)", without the diamong operator and it's called raw type.
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

class CustomListIterator<T> implements Iterator<T> {

    private List<T> source = null;
    private int index = 0;

    public CustomListIterator(List<T> source) {
        this.source = source;
    }

    @Override
    public boolean hasNext() { return this.index < this.source.size(); }

    @Override
    public T next() { return this.source.get(index++); }
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
