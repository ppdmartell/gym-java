/*
When serializing, the JVM takes care of which fields to serialize or not, following certain rules
such as: don't serialize neither transient fields nor static. But what if you have a class with a
lot of member variables (say 20)? And you don't want to serialize all the 20 fields, only 2 of them,
that means would will have to mark 18 member variables as transient (in case none of them are
static) and this could be annoying and time consuming. So the interface Externalizable helps with
this by following a different approach: Tell me only the fields you want to serialize and don't
mind about marking the rest of the member variables as transient.
By implementing the Externalizable interface, you will have to override two methods:
- writeExternal(ObjectOutput)
- readExternal(ObjectInput)

Something to note is that with Serializable the serialization is controlled by the JVM, meanwhile
with Externalizable the serialization is controller by the developer.

VERY IMPORTANT:
Externalizable interface requires a public no-args constructor in order to work properly, otherwise
you will get an exception issue.

SERIALIZATION vs EXTERNALIZATION (and serialization vulnerabilities) in [4].

Serialization vulnerabilities in [4](7:30min) 

Resources:
[1] https://www.youtube.com/watch?v=nUFoDfGl1II
[2] https://docs.oracle.com/javase/8/docs/api/java/io/Externalizable.html
[3] https://docs.oracle.com/javase/8/docs/api/java/io/ObjectOutput.html
[4] https://www.youtube.com/watch?v=Eo3mtkiB2-0
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Externalizable;

class App {
	public static void main(String[] args) {
		/*This example will try to serialize an object of class Employee into a file
		and then deserialize it pretending to be the endpoint module receiving
		the object as a file. In principle, salary shouldn't be serialized since it's transient.*/
		Employee employee = new Employee("Fermine", 45, 724.177);

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

class Employee implements Externalizable {
	private static final long serialVersionUID = 24L; //This class in the other module must have the same version. Add +1 every time you make a change and replicate the class in the other module.
	private String name; //Let's remove the final from name so we can assign the deserialized value.
	private final int age;
	private final Double salary;   //Let's remove the transient keyword here and still not serializing the salary by using Externalizable capabilities.
	private static final int CLAZZ = 1;

	public Employee(String name, int age, Double salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	//The no-args constructor is required and also to initialize the final variables.
	public Employee() {
		this.age = 0;
		this.salary = 0d;
	}


	public String getName() { return name; }
	public int getAge() { return age; }
	public Double getSalary() { return salary; }
	public int getClazz() { return CLAZZ; }

	@Override
	public String toString() {
		return "[name=" + name  + ",age=" + age  + ",salary=" + salary  + ",CLAZZ=" + CLAZZ  + "]";
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name); //Let's serialize only the name. writeUTF() is the method for String inherited from DataOutput. See [3].
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.name = in.readUTF();
	}
}
