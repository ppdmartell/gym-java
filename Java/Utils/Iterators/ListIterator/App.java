/*
ListIterator is a slightly more advanced iterator which can be used to iterate the elements
of a list in both directions, forwards and backwards [1].

Resources:
[1] https://www.youtube.com/watch?v=mzpgeRuYduY
*/


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

class App {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("element1");
        list.add("element2");
        list.add("element3");
        list.add("element4");

        ListIterator<String> listIterator = list.listIterator();
        System.out.println("---------FORWARDS----------");
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        System.out.println("---------BACKWARDS---------");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }
}
