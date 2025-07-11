import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

class Example3 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 39));

        sortClassic(people);
        System.out.println("-----------------------------------------------");
        printClassic(people);
        System.out.println("-----------------------------------------------");
        printLastNameWithStartingC(people);
        System.out.println("-----------------------------------------------");
        printLastNameWithStartingCLambda(people);
        System.out.println("-----------------------------------------------");
        sortLambda(people);
        System.out.println("-----------------------------------------------");
        printLambda(people);
        System.out.println("----------------using forEach------------------");
        people.forEach(p -> System.out.println(Thread.currentThread().getName() + ": " + p));
    }

    private static void sortClassic(List<Person> people) {
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.lastName().compareTo(p2.lastName());
            }
        });
    }

    private static void printClassic(List<Person> people) {
        for(Person p : people) {
            System.out.println(p);
        }
    }

    private static void printLastNameWithStartingC(List<Person> people) {
        for(Person p : people) {
            if(p.lastName().startsWith("C")) {
                System.out.println(p);
            }
        }
    }
    
    private static void sortLambda(List<Person> people) {
        Collections.sort(people, (p1, p2) -> p2.lastName().compareTo(p1.lastName()));
    }

    private static void printLambda(List<Person> people) {
        people.stream()
              .forEach(System.out::println);
    }

    private static void printLastNameWithStartingCLambda(List<Person> people) {
        people.stream()
              .filter(p -> p.lastName().startsWith("C"))
              .forEach(System.out::println);
    }
}
