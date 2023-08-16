/*
This example is about Selection sorting algorithm and is based on [1]. While sorting, this
algorithm virtually divides the input array into two sub-arrays: a sorted one (initially empty)
and the unsorted one which is the original one. Actually there is only one array the entire time,
the input array, you just swap elements but the starting counter is increased every time, so it
seems as if you were using two arrays somehow. Let's go descending this time.


	Time complexity:                                    Space complexity:
	-------------------------------------               ---------------------
	|  Best |   Average     |   Worst   |               |       Worst       |
	-------------------------------------               ---------------------
	|  Ω(n) |   Θ(n^2)      |   O(n^2)  |               |       O(n)        |
	-------------------------------------               ---------------------


Resources:
[1] https://www.youtube.com/watch?v=bIb9oX_d5qY&list=PLlsmxlJgn1HLCmaF51i5xAbgv1f49CsoP&index=2
[2] https://www.bigocheatsheet.com/
*/

import java.util.Arrays;

class App {

	private static final int[] arr = {10, 8, 1, 17, -2, 4};

	public static void main(String[] args) {
		System.out.printf("Initial array: %s%n", Arrays.toString(arr));
		sort();
	}

	private static void sort() {
		for (int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] > arr[index]) {
					index = j;
				}
			}
			if (index != i) {
				int aux = arr[i];
				arr[i] = arr[index];
				arr[index] = aux;
			}
		}
		System.out.printf("Ordered array: %s%n", Arrays.toString(arr));
	}
}
