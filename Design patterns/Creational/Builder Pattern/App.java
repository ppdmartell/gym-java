/*
According to what I have deduced, the Builder Pattern is used in the GoF book to help making objects immutable,
that's why the class fields are declared as final, with not setters and only getters. Making them immutable
helps with concurrency and disallowed tampering.
Another purpose of the Builder Pattern is flexibility at the time of the object creation, you are not forced
to set all the attributes and also don't need to declare several constructors.

Notes:
- If you are using an inner-builder, the Car class constructor should be declared as private. Otherwise, the
Car class constructor should be declared as default (no explicit access modifier). The reason is to force
the users of your class to use the builder to instantiate the Car object (encapsulation).
- The inner class CarBuilder should be declared both public and static. This way you can call it from classes
in other packages and without instantiate it.
- With the Builder Pattern, once you create the object with the desired parameters (but not all of them), you can't
set more parameters because it is supposed to be immutable. If you want to do that you need to create another object
using the Builder again.
- The object must be created and assigned using "new Car.CarBuilder()". Pay special attention to the no-args constructor
while creating the object.
*/

public class App {
	public static void main(String[] args) {
		Car car = new Car.CarBuilder()
                     .brand("Ferrari")
                     .model("Lolo")
                     .build();
		System.out.println(car);

		/*
			It is important to note how this pattern forces us to use the Builder Pattern
			to create the Car object and to be immutable.Now the "car" object is created
			we are unable to use setters (just doesn't have them) to modify its state.
			And since the Car class's constructor is private, we can't instantiate it directly.
		*/
	}
}

class Car {
	private final long id;
	private final String brand;
	private final String model;
	private final String color;
	private final int fuelCapacity;
	private final int powerHorses;
	private final int year;
	private final double price;

	private Car(CarBuilder builder) {
		this.id = builder.id;
		this.brand = builder.brand;
		this.model = builder.model;
		this.color = builder.color;
		this.fuelCapacity = builder.fuelCapacity;
		this.powerHorses = builder.powerHorses;
		this.year = builder.year;
		this.price = builder.price;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", brand=" + brand + ", model=" + model + ", color=" + color
             + ", fuelCapacity=" + fuelCapacity + ", powerHorses=" + powerHorses
             + ", year=" + year + ", price=" + price + "]";
	}

	//Getters and setters for class Car

	public static class CarBuilder {
		private long id;
		private String brand;
		private String model;
		private String color;
		private int fuelCapacity;
		private int powerHorses;
		private int year;
		private double price;

		public CarBuilder id(long id) {
			this.id = id;
			return this;
		}

		public CarBuilder brand(String brand) {
			this.brand = brand;
			return this;
		}

		public CarBuilder model(String model) {
			this.model = model;
			return this;
		}

		public CarBuilder color(String color) {
			this.color = color;
			return this;
		}

		public CarBuilder fuelCapacity(int fuelCapacity) {
			this.fuelCapacity = fuelCapacity;
			return this;
		}

		public CarBuilder powerHorses(int powerHorses) {
			this.powerHorses = powerHorses;
			return this;
		}

		public CarBuilder year(int year) {
			this.year = year;
			return this;
		}

		public CarBuilder price(double price) {
			this.price = price;
			return this;
		}

		public Car build() {
			Car car = new Car(this);
			return car;
		}
	}
}
