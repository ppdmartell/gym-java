/*
This code was to test why those lines weren't being executed in a static block while using a
multi-module application (HairdresserBookingSystem from the linkedin course exercise).
*/


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


class App4 {
	public static void main(String[] args) {
		try{
        	CompletableFuture.runAsync(() -> System.out.println("First executed."))
                         .thenRunAsync(() -> System.out.println("Second executed")).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

		System.out.println("END OF EXECUTION.");
	}
}
