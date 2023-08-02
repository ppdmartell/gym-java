/*
The Singleton Pattern allows you to create one and only one instance of a class (optimally accessible from everywhere).
Do not confuse unique with immutable. Even when the object is unique, doesn't have to be immutable.

Notes:
- Pay special attention at the lines of code handling multi-threading, one unique object is obviously exposed to being
used by several threads and maybe even several parts of the application. Multi-threading handling is a must for this pattern.

Resources:
[1] https://www.youtube.com/watch?v=tSZn4wkBIu8
*/

public class App {
	public static void main(String[] args) {
		Singleton obj = Singleton.getInstance("New object");
		System.out.println(obj.getData());
		obj.setData("Modified object");        // Here we modify the FIELD data of the object, but don't create a new one.
		System.out.println(obj.getData());
		Singleton obj2 = Singleton.getInstance("Attempting to create a new object"); // We try to create a new object, but Singleton Pattern just won't allow it!
		System.out.println("But in reality still is: " + obj2.getData());
	}
}

final class Singleton {

	private volatile static Singleton instance;
	private volatile String data;

	public String getData() {
		return data == null ? "" : data;
	}

	public synchronized void setData(String data) {
		this.data = data;
	}

	private Singleton(String data) {
		this.data = data;
	}

	public static Singleton getInstance(String data) {
		if(instance == null) {    /* This line prevents the threads to wait for the lock in case
                                     the instance is already created. Why waiting? It's already created.
									 the synchronization is limited only to the creation of the object.
									 On the other hand, retrieving operations can access it without the waiting limitation.
								  */
			synchronized (Singleton.class) {
				if(instance == null) {
					instance = new Singleton(data);
				}
			}
		}
		return instance;
	}

}
