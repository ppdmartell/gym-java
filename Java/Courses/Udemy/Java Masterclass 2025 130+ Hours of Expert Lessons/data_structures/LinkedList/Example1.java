import java.util.List;
import java.util.LinkedList;

class Example1 {

    public static void main(String[] args) {

        List<String> places = new LinkedList<>();
        places.add("Sydney");
        places.add(0, "Canberra");
        System.out.println(places);

        addMoreElements(places);        // Don't do (LinkedList<String> places). Casting will break the abstraction of using List interface in first place.
        System.out.println(places);

        removeElements(places);
    }

    private static void addMoreElements(List<String> list) {
        if (list instanceof LinkedList<String> linked) {      // Keep it as List and check if is instanceof
            linked.addFirst("Darwin");
            linked.addLast("Hobart");
            // Queue methods
            linked.offer("Melbourne");
            linked.offerFirst("Brisbane");
            linked.offerLast("Toowoomba");
            // Stack methods
            linked.push("Alice Springs");
        } else {
            System.out.println("Not a LinkedList, methods above can't be used.");
        }
    }

    private static void removeElements(List<String> list) {
        list.remove(4);
        list.remove("Brisbane");

        System.out.println(list);

        // remove(), push(), pop(), poll(), pollFirst() and pollLast() methods from the video can't be used unless using instanceof, since they are present in LinkedList and not List. 
    }
}
