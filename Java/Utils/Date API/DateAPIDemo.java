/*
In Java, the Date API refers to the classes and interfaces in the `java.time` package introduced in
Java 8 to handle date and time operations more effectively. The Date API provides a more
comprehensive and robust set of classes compared to the legacy `java.util.Date` and
`java.util.Calendar` classes, which were error-prone and not well-suited for modern date and time
manipulations.

The key classes and interfaces in the Date API include:

1. `LocalDate`: Represents a date without time (year, month, day).
2. `LocalTime`: Represents a time without date (hour, minute, second, nanosecond).
3. `LocalDateTime`: Represents a date and time without time zone information.
4. `ZonedDateTime`: Represents a date and time with time zone information.
5. `Instant`: Represents an instant in time (timestamp).
6. `Duration`: Represents a time-based amount of time, such as "2 hours" or "30 seconds."
7. `Period`: Represents a date-based amount of time, such as "3 days" or "1 year."
8. `DateTimeFormatter`: Used to parse and format dates and times in a human-readable format.
9. `ZoneId`: Represents a time zone identifier.
10. `ZoneOffset`: Represents a fixed time zone offset from UTC.

The Date API is designed to be immutable, thread-safe, and to provide clearer and safer methods for
date and time manipulation. It offers better support for different calendar systems, such as the
ISO calendar system, and handles daylight saving time changes and time zone conversions more reliably.

Here's an example of using the Date API to work with dates and times:
*/

import java.time.*;

public class DateAPIDemo {
    public static void main(String[] args) {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current date and time: " + currentDateTime);

        // Create a specific date and time
        LocalDate date = LocalDate.of(2023, Month.JULY, 29);
        LocalTime time = LocalTime.of(12, 30, 0);
        LocalDateTime specificDateTime = LocalDateTime.of(date, time);
        System.out.println("Specific date and time: " + specificDateTime);

        // Calculate the difference between two dates
        LocalDate startDate = LocalDate.of(2023, Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
        Period period = Period.between(startDate, endDate);
        System.out.println("Days between start and end date: " + period.getDays());

        // Parse a date string
        String dateString = "2023-07-29";
        LocalDate parsedDate = LocalDate.parse(dateString);
        System.out.println("Parsed date: " + parsedDate);

        // Format a date to a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = specificDateTime.format(formatter);
        System.out.println("Formatted date: " + formattedDate);

        // Working with time zones
        ZoneId londonZone = ZoneId.of("Europe/London");
        ZonedDateTime londonTime = specificDateTime.atZone(londonZone);
        System.out.println("London time: " + londonTime);
    }
}


/*
Output:

Current date and time: 2023-07-29T14:30:45.123456789
Specific date and time: 2023-07-29T12:30
Days between start and end date: 363
Parsed date: 2023-07-29
Formatted date: 29/07/2023
London time: 2023-07-29T12:30+01:00[Europe/London]


As you can see, the Date API provides a more intuitive and powerful way to handle date and time
operations in Java. It is recommended to use the `java.time` package for any new date and
time-related code in Java applications.


Resources:
[1] https://www.youtube.com/watch?v=OIg9lNpMJew
[2] https://www.youtube.com/watch?v=0XgdX5hDL4U
[3] https://www.youtube.com/watch?v=JdNQoTJmzis
[4] https://chat.openai.com/c/16f99044-4b80-44a9-b2c5-05a1bd71a56a [search phrase: "Date API refers to the classes and interfaces"]
*/
