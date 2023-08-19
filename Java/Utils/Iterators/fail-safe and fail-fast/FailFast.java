/*
Sometimes we need to iterate over a collection of elements and then we feel the need to add or
remove elements from this collection while iterating. This is tricky because, what if several
threads are traversing this collection and with one of them you decide to add/delete one or more
elements? You would have a problem of data consistency. That's why there are fail-safe iterators
and they will throw a ConcurrentModificationException. This example is based on [1].

However, there is a way to remove an element from the collection and is using the Iterator's remove()
method.

Resources:
[1] https://www.youtube.com/watch?v=PILSlTw4ZDc
[2] https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html#remove--
*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class FailFast {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Tomcat");
        list.add("Apache");
        Iterator<String> it = list.iterator();  //ArrayList data structure returns a fail-fast iterator.
        System.out.println("--------------------------------------------------------------------------");
        while(it.hasNext()) {
            String element = it.next();
            System.out.println(element);
            //it.add("Jetty");  //java.util.ConcurrentModificationException is thrown here.
            if(element.equals("Tomcat")) it.remove(); //[2]
        }
        System.out.println("--------------------------------------------------------------------------");
        list.stream().forEach(System.out::println);
    }
}
