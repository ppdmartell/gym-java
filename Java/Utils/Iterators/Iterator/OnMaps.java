/*
README: Maybe HashMap is not a data structure suitable for performing iterations since its
strengths lie in other features, for example the containsKey() and get() methods which have O(1)
constant average computational time complexity. Combined with AD (Addition and Deletion) operations
which are also O(1) on average.

A Map[1] is a key-value hash-based data structure so the elements are stored in buckets using a hash code,
meaning they are not stored sequentially, so they are scattered usually. You can iterate (traverse) over
a Map structure, say a HashMap, in several ways being using an Iterator, stream, enhanced for loop,
and probably others. The thing is, you don't iterate over the "original" structure, you iterate over
its entries, which is all the elements arranged sequentially.

Resources:
[1] https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
[2] https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
*/

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class OnMaps {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Sasha");
        map.put(2, "Natasha");
        map.put(3, "Ekaterina");
        map.put(4, "Nastya");
        map.put(5, "Irina");

        //Iterating using stream (see the entrySet() method call). Here is used parallelStream() for parallel access.
        map.entrySet().parallelStream().forEach(System.out::println);
        System.out.println("---------------------");

        //Iterating using an enhanced for loop.
        for(Map.Entry<Integer, String> element : map.entrySet()) {
            System.out.println(element);
        }
        System.out.println("---------------------");

        //Iterating using Iterator capbilities.
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(entry);
        }
    }
}
