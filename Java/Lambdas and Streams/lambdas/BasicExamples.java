/*
This file contains some knowledge about lambdas which are part of functional programming.
Functional programming lambdas allows you to assign behavior instead of just objects and
primitive types. The same way you can pass behaviors as a parameter to a function and even
return these behaviors as return types. They are known as "first-class language citizen".

Resources:
[1] https://www.youtube.com/watch?v=gpIUfj3KaOc&list=PLqq-6Pq4lTTa9YGfyhyW2CqdtW9RtY-I3
[2] https://www.youtube.com/watch?v=HsOVdmmBS9E

* Resource [1] is a playlist.

*/

class BasicExamples {
    public static void main(String[] args) {
        Print simplePrint = () -> System.out.println("This is an example of using a no-args lambda expression.");
        simplePrint.print();

        OneArgPrint oneArgPrint = p -> System.out.println("Let's print a one text passed as a parameter: " + p);
        oneArgPrint.print("Lambdas are handy!");

        TwoArgsPrint twoArgsPrint = (a, b) -> System.out.println("This lambda receives and prints two args (String[" + a + "] and int[" + b + "]).");
        twoArgsPrint.print("Messi", 10);

        TwoNumSum twoNumSum = (a, b) -> a + b;
        Double result = twoNumSum.sum(12.12312, 5.1231);
        System.out.println("The result of the two-numbers-sum lambda is: " + result);

        TwoNumMult<Double> twoNumMult = (a, b) -> a * b;
        System.out.println(twoNumMult.mult(20d, 20d));

        TwoNumMultModified<Double, Integer> multModified = (a, b) -> a * b;
        System.out.println(multModified.mult(50d,12));
    }
}

@FunctionalInterface
interface Print {
    void print();
}

@FunctionalInterface
interface OneArgPrint {
    void print(String text);
}

@FunctionalInterface
interface TwoArgsPrint {
    void print(String a, int b);
}

@FunctionalInterface
interface TwoNumSum {
    Double sum(Double a, Double b);
}

@FunctionalInterface
interface TwoNumMult<T extends Number> {
    T mult(T a, T b);
}

@FunctionalInterface
interface TwoNumMultModified<T extends Number, V extends Number> {
    T mult(T a, V b);
}
