/*
Resources:
[1] https://chat.openai.com/c/86acd6d2-b3de-493c-8acb-065c9ac93f31 [search phrase: "with multi-module applications"]
*/

package scheduling;

import employees.Hairdresser;
import java.util.concurrent.CompletableFuture;
import java.util.HashMap;
import java.util.Map;

public final class Scheduler {
    
    private static final Map<String, Hairdresser> hairdressers = new HashMap<>();
    
    public Scheduler() {
        Hairdresser harry = new Hairdresser("Harry");
        Hairdresser jill = new Hairdresser("Jill");
        
        CompletableFuture<Void> taskHarry = CompletableFuture.runAsync(() -> harry.setDaysAvailable(Days.MONDAY, Days.TUESDAY));
        CompletableFuture<Void> taskJill = CompletableFuture.runAsync(() -> jill.setDaysAvailable(Days.WEDNESDAY));

        // This way both tasks should be executed asynchronously by using allOf().
        // Then wait for both tasks to complete (optional)
        CompletableFuture.allOf(taskHarry, taskJill).join(); 
        
        hairdressers.put("harry", harry);
        hairdressers.put("jill", jill);
    }
    
    /*static {
        /*ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> harry.setDaysAvailable(Days.MONDAY, Days.TUESDAY));
        executor.execute(() -> jill.setDaysAvailable(Days.WEDNESDAY));
        executor.shutdownNow();*/
        
        /*
        I made a terrible mistake in the commented code above. You shouldn't use threads without
        having a mechanism to stop the execution of the program until the threads are finished.
        For example, I thought that by using executor.shutdown() it would wait for the threads to
        finish, but wasn't the case. So the more recommended mechanism is to use some sort of
        get() IN CASE THE NEXT LINES ARE RELATED TO THE THREADS, otherwise should be fine to use
        shutdown(). So the bottom line is to use .get() if the subsequent statements depend on the
        execution of the threads. For example, let's say you retrieve some data and must:
        
        1. Persist it in the database
        2. Send a notification email
        3. Print a simple message of execution done
        
        Since 1 and 2 are not related to 3, in principle you should use concurrency (e.g. Executor Service)
        to handle them and let the normal execution continue since 3 won't need (not be affected) by the
        successful completion of 1 and 2.
        
        In the present case, let's use some waiting mechanism with .get().
    
        SECOND ISSUE: I was trying to execute the code (now in the constructor) and for some reason
        I don't know, the CompletableFuture was getting deadlocked. But once I moved it to the
        constructor it started to work as intended. See 
        */

    //}
    
    public void bookHaircut(String hairdresserName, Days day) {
        Hairdresser hairdresser = hairdressers.get(hairdresserName.toLowerCase());
        if(hairdresser.getDaysAvailable().contains(day)) {
            hairdresser.removeDay(day);
            System.out.printf("You have booked %s on %s.%n", hairdresser.getName(), day.getString());
        } else {
            System.out.printf("%s is not available on %s.%n", hairdresser.getName(), day.getString());
        }
    }
}
