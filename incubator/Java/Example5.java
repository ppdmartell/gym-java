/*
This small program clarifies one of my doubts, the Collections.unmodifiable() method returns
an immutable list, but DOES NOT turn the original list into an immutable one.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Example5 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("element1", "element2", "element3"));
        List<String> list2 = Collections.unmodifiableList(list);
        list.add("element4");
        list.stream().forEach(System.out::println);
        list2.add("element4");   //UnsupportedOperationException because this one is the immutable one.
    }
}
