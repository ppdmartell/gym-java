/*
This example is about Merge sort and is based on [1]. This algorithm consists of two major steps,
divide and conquer. Basically it divides the array into pieces to "localize" the comparing process
and once the elements are compared in the smallest step, they are compared again and again to the
created "bigger" sub-arrays, until there is only one array, which is the final one and is ordered.
This dividing process leads to arrays of only a single element, and from there it starts to build
up by comparing to other sub-arrays, then join, and then compare again, and then join again, all
this until as said before, there final array is the same initial unordered array but ordered.
If the initial array (or suitable data structure) has 8 elements, it will be divided into 8 small
one-element sub-arrays, and start the comparing process from the ground up by joining them in
each step until reaching an ordered array of 8 elements.
It's a "divide and conquer" algorithm, being the conquer part the comparing process at each step.


    Time complexity:                                                Space complexity:
    -----------------------------------------------------           ---------------------
    |  Best         |   Average          |  Worst       |           |       Worst       |
    -----------------------------------------------------           ---------------------
    |  Ω(n log(n))  |   Θ(n log(n))      |  O(n log(n)) |           |       O(n)        |
    -----------------------------------------------------           ---------------------


Resources:
[1] https://www.youtube.com/watch?v=U4g1dMry4W4&list=PLlsmxlJgn1HLCmaF51i5xAbgv1f49CsoP&index=5
[2] https://www.bigocheatsheet.com/
[3] https://github.com/eugenp/tutorials/blob/master/algorithms-modules/algorithms-sorting/src/main/java/com/baeldung/algorithms/mergesort/MergeSort.java
[4] https://github.com/eugenp/tutorials/tree/master/algorithms-modules/algorithms-sorting
*/

import java.util.Arrays;

class App {

    private static final int[] arr = { 14, 4, 17, 12, -6, 3, 8, 2 };

    public static void main(String[] args) {
        System.out.printf("Initial array: %s%n", Arrays.toString(arr));
        sort(arr, arr.length);
        System.out.printf("Ordered array: %s%n", Arrays.toString(arr));
    }

    private static void sort(int[] a, int n) {
        if (n < 2)
            return;
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }

        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        sort(l, mid);
        sort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    private static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {
            if (l[i] <= r[j])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];
        }

        while (i < left)
            a[k++] = l[i++];

        while (j < right)
            a[k++] = r[j++];
    }
}
