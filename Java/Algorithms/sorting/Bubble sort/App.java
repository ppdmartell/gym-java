/*
The bubble sort algorithm compares adjacent elements of an array and use an auxiliar variable
to hold one of the elements meanwhile the swapping is being done. The name "bubble" comes because
when sorting the elements, if ascending all the small elements "bubble up" to the back of the array
and the other way around with if descending.
Generics is not used here because I would prefer to keep it simple, although using generics as in
[1] is a very powerful technique for comparing arrays of different data types. Let's go ascending.


	Time complexity:									Space complexity:
	-------------------------------------				---------------------
	|  Best	|	Average		|	Worst	|				|		Worst		|
	-------------------------------------				---------------------
	|  Ω(n)	|	Θ(n^2)		|	O(n^2)	|				|		O(n)		|
	-------------------------------------				---------------------



Resources:
[1] https://www.youtube.com/watch?v=F9F9TXq9Fh4&list=PLlsmxlJgn1HLCmaF51i5xAbgv1f49CsoP
[2] https://www.bigocheatsheet.com/
*/

import java.util.Arrays;

class App {

	private static final int[] array = { 4, 7, 12, 89, 32, 5, 1, 11, 68, 8 };


	public static void main(String[] args) {
		sort();
		sortEnhanced();
		sortFurtherEnhanced();
	}

	public static void print(long time, String methodName) {
		System.out.printf("Sorted with %s in %d nanoseconds: %s%n", methodName, time * -1, Arrays.toString(array));
	}

	private static void sort() {
		long time = System.nanoTime();           //System.currentTimeMillis(); for milliseconds
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				int aux = 0;
				if (array[j] > array[j + 1]) {
					aux = array[j];
					array[j] = array[j + 1];
					array[j + 1] = aux;
				}
			}
		}
		time -= System.nanoTime();
		print(time, "sort()");
	}

	private static void sortEnhanced() {        //This is basically the same method, but why comparing the elements already sorted at the end of the array?
    long time = System.nanoTime();
    for (int i = 0; i < array.length - i - 1; i++) {    //The tweak is here "array.length - i -1"
        for (int j = 0; j < array.length - 1; j++) {
            int aux = 0;
            if (array[j] > array[j + 1]) {
                aux = array[j];
                array[j] = array[j + 1];
                array[j + 1] = aux;
            }
        }
    }
    	time -= System.nanoTime();
   		print(time, "sortEnhanced()");
	}

	/*The tweak here comes after the questions: If there are already x elements ordered, why going
      through all the x times with the outter loop?
	  If practically there are needed, e.g. 3 iterations in [1], why going through all the 6?.
	  With the current array, the time reduction is around ten times from the normal sort() method.
	*/
	private static void sortFurtherEnhanced() {
    	long time = System.nanoTime();
		boolean swap = false;
    	for (int i = 0; i < array.length - i - 1; i++) {
			swap = false;
        	for (int j = 0; j < array.length - 1; j++) {
            	int aux = 0;
            	if (array[j] > array[j + 1]) {
                	aux = array[j];
                	array[j] = array[j + 1];
                	array[j + 1] = aux;
					swap = true;
            	}
        	}
			if (!swap) break;
    	}
    	time -= System.nanoTime();
    	print(time, "sortFurtherEnhanced()");
	}
}
