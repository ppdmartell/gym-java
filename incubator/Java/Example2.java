import java.util.Arrays;
import java.util.stream.IntStream;

class Exercise6 {
    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,5,6,7,8};
        IntStream s = Arrays.stream(numbers);
        s.filter(p -> p % 2 == 0).forEach(p -> System.out.println(p));
		Arrays.stream(numbers).filter(p -> p % 2 == 0).forEach(System.out::println);  //Does what lines 7 and 8 do but in a one-liner.
    }
}
