/*
Enums are basically a concept in Java of things that don't ever change, ergo they are immutable
and thread-safe by nature. For example the days of the week, makes no sense to write a program
to add an 8th day. This example is based on [1].
By convention, the fields of an enum are written in upper-case.


Resources:
[1] https://www.youtube.com/watch?v=wq9SJb8VeyM
*/

import java.util.Arrays;

class App {
	public static void main(String[] args) {
		Days day = Days.FRIDAY;
		if(day == Days.FRIDAY) System.out.println("It's friday already!");
		else System.out.println("Not friday yet.");

		System.out.printf("My level of stress on Fridays is %d.%n", Days.FRIDAY.stressLevel);
		System.out.printf("Time for Tuesday is %.1f.%n", Days.TUESDAY.time);

		Arrays.stream(Days.values()).forEach(System.out::println);
	}
}

enum Days {
	SUNDAY(0, 1d),
	MONDAY(1, 2d),
	TUESDAY(2, 3d),
	WEDNESDAY(3, 4d),
	THURSDAY(4, 5d),
	FRIDAY(5, 6d),
	SATURDAY(6, 7d);

	final int stressLevel;
	final double time;

	Days(int stressLevel, double time) {
		this.stressLevel = stressLevel;
		this.time = time;
	}
}
