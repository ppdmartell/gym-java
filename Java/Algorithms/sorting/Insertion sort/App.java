/*
This example is about Insertion sort algorithm and is based on [1]. This algorithm will try to
insert an element among others, based on a comparing criterion. Hence the name. The array will be
ordered in an ascending way.


    Time complexity:                                    Space complexity:
    -------------------------------------               ---------------------
    |  Best |   Average     |   Worst   |               |       Worst       |
    -------------------------------------               ---------------------
    |  Ω(n) |   Θ(n^2)      |   O(n^2)  |               |       O(n)        |
    -------------------------------------               ---------------------


Resources:
[1] https://www.youtube.com/watch?v=ZUZi8HJY8kc&list=PLlsmxlJgn1HLCmaF51i5xAbgv1f49CsoP&index=3
[2] https://www.bigocheatsheet.com/
*/

import java.util.Arrays;

class App {

	private static final int[] arr = { 3, 22, 7, -1, 25, 10, 15 };

	public static void main(String[] args) {
		System.out.printf("Initial array: %s%n", Arrays.toString(arr));
		sort();
	}

	private static void sort() {
		int aux = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--) {
					aux = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = aux;
			}
		}
		System.out.printf("Ordered array: %s%n", Arrays.toString(arr));
	}
}
