/*
This example is about Quick sort algorithm and is based on [1]. Quick sort algorithm selects and
element in the array and then groups the remaining elements into two categories, whether they are
bigger or smaller than the selected element. Quick sort algorithm is considered an in-place
algorithm, meaning you don't need extra space to allocate elements from the initial data-structure
so you can iterate and order at the same time using the original data-structure.
The first step is selecting a "pivot" element, the one which the categorizing will be based upon.

    Time complexity:                                             	Space complexity:
    -----------------------------------------------------        	---------------------
    |  Best         |   Average          |  Worst       |        	|       Worst       |
    -----------------------------------------------------        	---------------------
    |  Ω(n log(n))  |   Θ(n log(n))      |  O(n^2)      |           |     O(log(n))     |
    -----------------------------------------------------        	---------------------


Resources:
[1] https://www.youtube.com/watch?v=aY0yYfztKMY&list=PLlsmxlJgn1HLCmaF51i5xAbgv1f49CsoP&index=6
[2] https://github.com/eugenp/tutorials/blob/master/algorithms-modules/algorithms-sorting/src/main/java/com/baeldung/algorithms/quicksort/QuickSort.java
[3] https://www.bigocheatsheet.com/
*/

import java.util.Arrays;

class App {

	private static final int[] arr = { 8, 30, 10, 22, 35, -29, 41 };

	public static void main(String[] args) {
		System.out.printf("Initial array: %s%n", Arrays.toString(arr));
		sort(arr, 0, arr.length - 1);
		System.out.printf("Ordered array: %s%n", Arrays.toString(arr));
	}

	private static void sort(int a[], int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(a, begin, end);
			sort(a, begin, partitionIndex - 1);
			sort(a, partitionIndex + 1, end);
		}
	}

	private static int partition(int[] a, int begin, int end) {
		int pivot = a[end];
		int i = begin - 1;
		int aux = 0;

		for (int j = begin; j < end; j++) {
			if (a[j] <= pivot) {
				i++;
				aux = a[i];
				a[i] = a[j];
				a[j] = aux;
			}
		}

		aux = a[i + 1];
		a[i + 1] = a[end];
		a[end] = aux;

		return i + 1;
	}
}
