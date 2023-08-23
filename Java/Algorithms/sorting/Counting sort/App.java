/*
This example is about Counting sort algorithm and is based on [1].
USED TO SORT ONLY INTEGERS!

The integers stored inside the array to sort would be used as indices. If they are small, then
better.

Resources:
[1] https://www.youtube.com/watch?v=YEabFTMDczQ&list=PLlsmxlJgn1HLCmaF51i5xAbgv1f49CsoP&index=7
*/

import java.util.Arrays;

class App {

    private static final int[] arr = { 5, 2, 8, 7, -2, 2, 3, 3, 6, 2, 6, -1, 1, 2, 7, -2, 5, 2, 4, 9 };

    public static void main(String[] args) {
        System.out.printf("Initial array: %s%n", Arrays.toString(arr));
        sort(1);
        System.out.printf("ASC-ordered array: %s%n", Arrays.toString(arr));
        sort(-1);
        System.out.printf("DESC-ordered array: %s%n", Arrays.toString(arr));
    }

    private static void sort(int sense) {
        int min = Arrays.stream(arr).min().orElse(0);
        int max = Arrays.stream(arr).max().orElse(Integer.MAX_VALUE);

        int[] countArr = new int[max - min + 1];

        for (int value : arr) {
            countArr[value - min]++;
        }

        int index = 0;

        if (sense >= 0) {                               //ASCENDING sense case
            for (int i = 0; i < max - min + 1; i++) {
                while (countArr[i] > 0) {
                    arr[index] = i + min;
                    countArr[i]--;
                    index++;
                }
            }
        } else {                                        //DESCENDING sense case
            for (int i = max - min; i >= 0; i--) {
                while (countArr[i] > 0) {
                    arr[index] = i + min;
                    countArr[i]--;
                    index++;
                }
            }
        }
    }
}
