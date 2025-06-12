import java.util.Arrays;

class Example1 {

    public static void main(String[] args) {
        int[] array1 = new int[] {1, 2, 3, 4, 5, 6};
        int[] array2 = array1;
        array1[3] = 999;
        System.out.println("Array1: " + Arrays.toString(array1));
        System.out.println("Array2: " + Arrays.toString(array2));

    }

}
