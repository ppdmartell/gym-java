import java.util.Set;
import java.util.LinkedHashSet;

class Example {
	public static void main(String[] args) {
		Set<Integer> set = new LinkedHashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(2);
		set.stream().forEach(System.out::println);
	}
}
