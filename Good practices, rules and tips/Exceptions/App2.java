/*
This example will deal with try with resources. Try with resources was introduced in Java 7

By the way, starting with version 5, Java passed from using the naming convention from 1.X
to just X. Being 1.1, 1.2, 1.3,1.4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20.
Having LTS (Long-Term Support) versions 8, 11 and 17.

Sometimes you need to use resources that will possible throw eceptions, then you write code to
handle those exceptions, but at the end you always need to execute a finally block of code (not
always, just in this hypothetical case). For example to close a BufferedReader, or to shutdown
an ExecutorService, releasing the device's resources in any possible outcome. One of the things
try with resources helps you to do is to about this finally block and just put it in the try
initial declaration.

But in order to do this, the resource must do the next:
In Java, for a class to be used in a try-with-resources statement, it must meet the following
requirements:

1. Implement the `AutoCloseable` or `java.io.Closeable` interface: The class must implement either
the `AutoCloseable` interface (introduced in Java 7) or the `java.io.Closeable` interface
(available since Java 5). These interfaces provide the necessary contract for resource management,
requiring the class to have a `close()` method that releases any acquired resources.

2. The `close()` method: The class must provide an implementation of the `close()` method, which
is responsible for releasing resources held by the object. When the try-with-resources block exits
(either normally or due to an exception), the `close()` method will be called automatically.

For example, a simple class implementing the `AutoCloseable` interface would look like this:

public class MyResource implements AutoCloseable {

    // Constructor and other methods

    @Override
    public void close() {
        // Release any acquired resources here
        // This method will be called automatically when used in try-with-resources
    }
}


Once a class meets these requirements and implements the necessary interfaces, you can use it
within a try-with-resources statement. The try-with-resources statement ensures that the resource
is automatically closed, regardless of whether an exception occurs or not. This helps improve
resource management and ensures that resources are released properly after their usage. Here's
an example of using the `MyResource` class with try-with-resources:

public class Main {
    public static void main(String[] args) {
        try (MyResource resource = new MyResource()) {
            // Use the resource inside this block
            // The resource will be automatically closed after this block
        } catch (Exception e) {
            // Handle exceptions if necessary
        }
    }
}


In this example, the `close()` method of `MyResource` will be called automatically when the try
block exits, whether it's due to normal completion or an exception.

Resources:
[1] https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html
[2] https://docs.oracle.com/javase/8/docs/api/java/io/Closeable.html
[3] https://chat.openai.com/c/16f99044-4b80-44a9-b2c5-05a1bd71a56a [search phrase: "try-with-resources statement"]
*/

class App2 {
	public static void main(String[] args) {
		try (ExampleResource resource = new ExampleResource()) {
			resource.calculate(77, 0);
		} catch (ArithmeticException e) {
			System.out.println("Division by zero is undefined.");
		}
	}
}

class ExampleResource implements AutoCloseable { //You can also implement Closeable interface (Java 5), although AutoCloseable was introduced in Java 7.
	@Override
	public void close() {
		System.out.println("This will be executed because this ExampleResource class implements AutoCloseable interface.");
	}

	public int calculate(int a, int b) throws ArithmeticException {
		return a / b;
	}
}
