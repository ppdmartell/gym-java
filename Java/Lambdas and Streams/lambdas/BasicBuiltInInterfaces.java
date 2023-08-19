/*
Starting from JDK 8, Java provides built-in out-of-the-box functional interfaces for
using with lambda expressions. This saves time and effort. They lie under the java.util.function package, see [2].

Resources:
[1] https://www.youtube.com/watch?v=yubVRLP9Htw&list=PLqq-6Pq4lTTa9YGfyhyW2CqdtW9RtY-I3&index=15&pp=iAQB
[2] https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
[3] https://www.youtube.com/watch?v=WcLum7g6ImU&list=PLqq-6Pq4lTTa9YGfyhyW2CqdtW9RtY-I3&index=19&pp=iAQB (about Closures, final and effectively final)
[4] https://www.youtube.com/watch?v=x9ovGHK7r2E&list=PLqq-6Pq4lTTa9YGfyhyW2CqdtW9RtY-I3&index=20&pp=iAQB (about the use of keyword this in lambda expressions)
*/

import java.util.function.BiConsumer;
import java.util.function.DoubleToIntFunction;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.BiPredicate;

class BasicBuiltInInterfaces {
    public static void main(String[] args) {
        BiConsumer<String, Double> biConsumer = (a, b) -> System.out.println("String: " + a + ", Double: " + b);
        biConsumer.accept("This is a text", 45.9);

        DoubleToIntFunction doubleToInt = a -> ((Double)a).intValue();
        System.out.println("The double value 487.23423 to Integer is: " + doubleToInt.applyAsInt(487.23423));

        Predicate<Integer> intPredicate = a -> a % 2 == 0;
        System.out.println("Is the number 5 even?: " + intPredicate.test(5));

        Function<String, Integer> function = String::length;
        String text = "The amount of characters of this text is: ";
        System.out.println(text + function.apply(text));

        Supplier<Long> supplier = () -> 4664L;
        System.out.println("An example of a Supplier<T> that returns a Long (T): " + supplier.get());

        //This is an excercise of the study-items file for the entering interview. Now I realize I didn't grasp the use of BiPredicate.
        BiPredicate<Integer,Integer> biPredicate = (a, b) -> {
            if(a % 2 == 0) {
                System.out.println("The sum of a[" + a + "] and b[" + b + "] is: " + (a + b));
                return true;
            }
            return false;
        };
        System.out.println("Is number 10 even?: " + biPredicate.test(10, 12));
    }
}


/*
Just in case, and to keep it here, an explanation about trying to modify the value of a variable
inside a lambda expression. It is not possible unless you use atomic types (e.g. AtomicInteger for an Integer-type
variable). This belongs to the Closures concept (see resource [3]).

Q: Assistant, if you use a variable inside a lambda function, but this variable was declared outside and before,
does this variable become immutable by definition? For example:

Integer value = 12;
Supplier<Integer> supplier = () -> {
       value += 1111;
       return value;
}

Can the variable value be changed inside the lambda expression?

A: In Java, the variables used in a lambda expression must be effectively final or effectively final.
An effectively final variable is one whose value does not change after it is initially assigned. It means
that you can use the variable inside the lambda expression, but you cannot modify its value.

In your example, the variable `value` is not effectively final because its value is modified inside
the lambda expression (`value += 1111`). Therefore, it will result in a compilation error.

If you want to modify the value of `value` inside the lambda expression, you need to use a mutable wrapper class,
such as `AtomicInteger`, which provides methods for atomic updates. Here's an example:


AtomicInteger value = new AtomicInteger(12);
Supplier<Integer> supplier = () -> {
    value.addAndGet(1111);
    return value.get();
};


In this example, the `AtomicInteger` class is used to wrap the `value` variable, allowing atomic updates to its value
inside the lambda expression.

By using a mutable wrapper class, you can achieve the desired behavior of modifying the value of a variable inside a lambda expression.
*/
