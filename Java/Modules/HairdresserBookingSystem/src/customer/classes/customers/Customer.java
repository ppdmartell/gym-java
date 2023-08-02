package customers;

import scheduling.Days;
import scheduling.Scheduler;  //Interesting, even when you are requiring "bookingsystem" which is the module
                              //what you import is the package inside the module by referencing it by its name
                              //which is "scheduling".

public final class Customer {
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        scheduler.bookHaircut("haRRy", Days.MONDAY);
        scheduler.bookHaircut("JiLL", Days.FRIDAY);
    }
}
