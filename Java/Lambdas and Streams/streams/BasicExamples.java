/*
In this Java file let's make some streams exercises.

6- Dada una collection de enteros, imprima los pares.
7-  Dada una colleciton de enteros, imprima los impares.
8- Dada una collection de enteros, imprima la cantidad de pares.
9- Dada una collecion de enteros, imprima la cantidad de impares.
10- Dada una collection de enteros, multiplique cada valor par por 2 e imprima cada elemento de la collection;
11- Dada una collection de enteros, multiplique cada valor impar por 20 e imprima cada elemento de la collection;
12- Devuelva una lista de los elementos pares.
13- Devuelva un set de los elementos impares.

Resources:
[1] https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
*/

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

class BasicExamples {
	public static void main(String[] args) {
		//List<Integer> list = new ArrayList<>();
		int[] i = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		System.out.println("Elements: " + Arrays.toString(i));
		System.out.println("------------------------------------------------------------------------------------");
		Arrays.stream(i).filter(p -> p % 2 == 0).forEach(System.out::println); //6
		System.out.println("------------------------------------------------------------------------------------");
		Arrays.stream(i).filter(p -> p % 2 != 0).forEach(System.out::println); //7
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("The amount of even elements is: " + Arrays.stream(i).filter(p -> p % 2 == 0).count()); //8
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("The amount of odd elements is: " + Arrays.stream(i).filter(p -> p % 2 != 0).count()); //9
		System.out.println("------------------------------------------------------------------------------------");
		Arrays.stream(i).map(p -> p % 2 == 0 ? p * 2 : p).forEach(System.out::println); //10
		System.out.println("------------------------------------------------------------------------------------");
		Arrays.stream(i).map(p -> p % 2 == 0 ? p : p * 20).forEach(System.out::println); //11
		System.out.println("------------------------------------------------------------------------------------");
		//List<Integer> list = Arrays.stream(i).filter(p -> p % 2 == 0).collect(Collectors.toList());
		System.out.println("List of even elements using collect() method: " + Arrays.stream(i).filter(p -> p % 2 == 0).boxed().collect(Collectors.toList())); //The use of boxed saved this excercise (if using int[] instead of Integer[])
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Set of even elements using collect() method: " + Arrays.stream(i).filter(p -> p % 2 != 0).boxed().collect(Collectors.toSet())); //13
}
}
