/*
Serialization is the representation of an object in memory in bytes. Serialization is usually used
for when you want to share that object to another system/devide/location to be deserialized and
used. For example, you could save the state of an object in a file and/or sending it over the
network (using sockets). Or say there two related programs(modules) from the same company and
they share the same implementation of a class, and at some point the state of an object of that
class in one module need to be used in the other module. Serialization would be very useful in
this scenario. In order to serialize an object, you need the class to meet certain conditions:

1. To implement Serializable interface (this tells the JVM to allow the class's object to be
serialized, mostly for security reasons).
2. The class must have a static field "private static final long serialVersionUID = 1L;". The
version of this class's field, in this case 1, should be the same in the class existing at the
other module that would be used to deserialize the object. Otherwise you will get an exception
(NotSerializableException).
3. Static fields belong to the class, so they won't be serialized.
4. Transient fields indicate not to serialize the field marked as such.

An important thing to note is a serialized object is platform-independent, because if you think
about it, a serialized object is the represetation in bytes of an object in memory. And every
device can read bytes and understand what they are.
Another form of serialization is XML and JSON, although that's a bit more advanced than this
simple and small example.

Resources:
[1] https://www.youtube.com/watch?v=nUFoDfGl1II
[2] https://www.youtube.com/watch?v=qo9S2CeoqQE
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class App {
	public static void main(String[] args) {
		/*This example will try to serialize an object of class Employee into a file
		and then deserialize it pretending to be the endpoint module receiving
		the object as a file. In principle, salary shouldn't be serialized since it's transient.*/
		Employee employee = new Employee("Marshall", 45, 324.343);

		//Serializing the Employee object.
		File file = new File("employee.serial");
		try (FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(employee);
		} catch (FileNotFoundException e) {
			System.out.printf("The file path can't be found: %s.%n", file.getAbsolutePath());
		} catch (IOException e) {
			System.out.printf("An I/O exception of some sort has occurred: %s.%n", e.getMessage());
		}

		//Deserializing the object from file "employee.serial" and printing its values.
		try (FileInputStream fis = new FileInputStream(file); //The same object file is being used, but it wouldn't exist in the other module, would have to be created. Here it's used to avoid creating another File object.
			ObjectInputStream ois = new ObjectInputStream(fis);) {
			Employee employee2 = (Employee) ois.readObject();
			System.out.printf("The deserialized object is: %s.%n", employee2);
		} catch (ClassNotFoundException e) {
			System.out.printf("Class of a serialized object cannot be found: %s.%n", employee.getClass().getCanonicalName());
		} catch (IOException e) {
			System.out.printf("An I/O exception of some sort has occurred: %s.%n", e.getMessage());
		}
	}
}

class Employee implements Serializable {
	private static final long serialVersionUID = 23L;
	private final String name;
	private final int age;
	private transient final Double salary;   //As intended, when the object is deserialized, salary will have a null value, even when it has a value when the first Employee object was created.

	public Employee(String name, int age, Double salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public String getName() { return name; }
	public int getAge() { return age; }
	public Double getSalary() { return salary; }

	@Override
	public String toString() {
		return "[name=" + name  + ",age=" + age  + ",salary=" + salary  + "]";
	}
}
