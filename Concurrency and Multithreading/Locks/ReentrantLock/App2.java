/*
Imagine you have a small cinema and want people to book seats via the online booking system.
If two people book the same seat at the same time there could be a problem, so in this case
a lock (threads maybe not so much due to the nature of the people booking,but let's keep it
with threads since we are already in this) could be a really good example to achieve this purpose.
This program is based in the idea in [1].
The cinema as 100 seats.

Resources:
[1] https://www.youtube.com/watch?v=ahBC69_iyk4
*/

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

class App2 {

	private static int takenSeats;
	private static Map<Integer, Booking> bookings;
	private static Lock lock;

	static {
		bookings = new HashMap<>();
		lock = new ReentrantLock();
		IntStream.range(1, 101).forEach(i -> bookings.put(i, null));
		bookings.entrySet().forEach(System.out::println);
	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		int requests = 1;
		for(;;) {
			int seatRequest = onlineSeatRequest();
			System.out.println("----------------------------------------------------------");
			System.out.printf("[%d] Booking request for seat number: %d\n", requests, seatRequest);
			requests++;
			executorService.submit(() -> { check(seatRequest, "ppdmartell"); });  //The username is hard-coded here, but in principle should be obtained from the logged user.
			if(takenSeats == 100) break;
		}
		executorService.shutdown();
		sanityCheck();
		System.out.printf("SOLD OUT! All %d seats were booked.\n", takenSeats);
	}

	private static void check(int seat, String user) {
		lock.lock();       //In this case is very important to lock for read and write! It performed glitchy when only writing was locked.
		try {
			if(bookings.get(seat) != null) System.out.printf("'---> [NOT POSSIBLE] Seat number %d is already booked.\n");
			else book(seat, user);
		} finally {
			lock.unlock();
		}
	}

	private static int onlineSeatRequest() {
		return new Random().nextInt(100) + 1;
	}

	private static void book(int seat, String user) {
		Booking booking = new Booking();
		booking.setSeat(seat);
		booking.setUser(user);
		booking.generateToken();
		bookings.put(seat, booking);
		takenSeats++;
		System.out.printf("'---> [DONE] Seat number %d has been booked, with token number %d\n", seat, booking.getGeneratedToken());
	}

	private static void notifyUnavailability(int seat) {
		System.out.printf("'---> [NOT POSSIBLE] Notifying, seat %d is already taken.\n", seat);
	}

	private static void sanityCheck() {
		Set<Long> tokens = new HashSet<>();
		System.out.printf("Amount of bookings: [%d][%d]\n", bookings.keySet().size(), bookings.values().size());
		bookings.values().stream().forEach(p -> tokens.add(p.getGeneratedToken()));
		System.out.printf("The amount of unique tokens is: %d\n", tokens.size());
	}
}

class Booking {
	private int seat;
	private String user;
	private long generatedToken;

	public int getSeat() { return seat; }
	public void setSeat(int seat) { this.seat = seat;}

	public String getUser() { return user; }
	public void setUser(String user) { this.user = user; }

	public void generateToken() {
		if(seat == 0) System.out.println("Unable to generate a token for an empty seat.");
		else generatedToken = new Random().nextLong(); //Imagine this is a hash or some unique key obtained with a function.
	}

	public long getGeneratedToken() { return generatedToken; }
}
