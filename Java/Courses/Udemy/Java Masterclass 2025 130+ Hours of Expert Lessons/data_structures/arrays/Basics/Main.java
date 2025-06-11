class Main {

    public static void main(String[] args) {

        double[] doubleArray = new double[10]; // Created and initialized but not populated
        
        int[] intArray = new int[] {1, 2, 3, 4, 5}; // Created, initialized and populated

        int[] anonymousIntArray = {6, 7, 8, 9, 10}; // Created and populated in an anonymous way, one-liner

        int[] arrayError;
        //arrayError = {3, 5, 8, 2}; // Error at compile time, anonymous array
        arrayError = new int[] {3, 5, 8, 2}; // But this won't error at compile


        System.out.printf("Size of the array intArray is: %d%n", intArray.length);
        System.out.printf("Element 4 is: %d", anonymousIntArray[3]);

        double[] elements = new double[] {1.2, 3.5, 7.3, 88.8};
        for (int i = 0; i < elements.length; i++) {
            System.out.print(elements[i] + " ");
        }
    }

}
