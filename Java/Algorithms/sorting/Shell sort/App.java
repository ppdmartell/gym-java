/*
This examples is about Shell sort algorithm and is based on [1]. The Shell sort algorithm is
considered a generalization of the Insertion sort, often referred to as an optimization.


    Time complexity:                                  				  		 Space complexity:
    ----------------------------------------------------------               ---------------------
    |      Best		|       Average		 |       Worst 		 |               |       Worst       |
    ----------------------------------------------------------               ---------------------
    |  Ω(n log(n))	|   Θ(n(log(n))^2)   |   O(n(log(n))^2)  |               |       O(n)        |
    ----------------------------------------------------------               ---------------------


Resources:
[1] https://www.youtube.com/watch?v=IViqgakt-Eg&list=PLlsmxlJgn1HLCmaF51i5xAbgv1f49CsoP&index=5
*/

import java.util.Arrays;

class App {

	private static final int[] arr = { 3, 22, 14, -1, 31, 10, 7, 25 };

	public static void main(String[] args) {
		System.out.printf("Initial array: %s%n", Arrays.toString(arr));
		sort();
	}

	private static void sort() {
		int aux = 0;
		for (int gap = arr.length / 2; gap > 0; gap /=2) {
			for (int i = gap; i < arr.length; i++) {
				for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--) {
					aux = arr[j];
					arr[j] = arr[j - gap];
					arr[j - gap] = aux;
					j -= gap;
				}
			}
		}
		System.out.printf("Ordered array: %s%n", Arrays.toString(arr));
	}
}
