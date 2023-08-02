import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) {

		Device gps1 = new GpsDevice(99.99, 1.0, 2.0);
		Device gps2 = new GpsDevice(55.50, 2.22, 3.33);

		Vehicle car = new Car("Audi", 4, 500, "Dark-gray", true, gps1);
		Vehicle bus = new Bus("School bus", 10, 1000, "Yellow", 60);
		Vehicle car2 = new Car("VW", 4, 300, "Purple", false, gps2);

		List<Vehicle> list = new ArrayList<>();
		list.add(car);
		list.add(bus);
		list.add(car2);
		cloneList(list);
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("Original list (" + list.size() + " elements): " + list);
		list.stream().filter(p -> p instanceof Car).forEach(p -> System.out.println(((Car)p).getDevice()));
	}

	public static void cloneList(List<Vehicle> list) {
		List<Vehicle> mirrorList = new ArrayList<>();
		list.stream().forEach(p -> {
			mirrorList.add(p.clone());
		});
		list.remove(2);
		System.out.println("The list has been cloned successfully (" + mirrorList.size() + " elements): " + mirrorList);
		mirrorList.stream().filter(p -> p instanceof Car).forEach(p -> System.out.println(((Car)p).getDevice()));
	}
}

abstract class Vehicle {
	private String brand;
	private int tires;
	private int fuelCapacity;
	private String color;

	public Vehicle() {}

	public Vehicle(String brand, int tires, int fuelCapacity, String color) {
		this.brand = brand;
		this.tires = tires;
		this.fuelCapacity = fuelCapacity;
		this.color = color;
	}

	protected Vehicle(Vehicle vehicle) {     //The constructor is declared a protected for only subclasses of Vehicle of classes in the same package can access it.
		this.brand = vehicle.brand;
		this.tires = vehicle.tires;
		this.fuelCapacity = vehicle.fuelCapacity;
		this.color = vehicle.color;
	}

	abstract public Vehicle clone();
}

class Car extends Vehicle {
	private boolean hasTrunk;
	private Device device;

	public Car() {}   //It's interesting, even when Car class extends Vehicle which has a no-args constructor, you must implement a Car class no-args constructor as well, otherwise you won't be able to create an object with a no-args constructor.

	public Car(String brand, int tires, int fuelCapacity, String color, boolean hasTrunk, Device device) {
		super(brand, tires, fuelCapacity, color);
		this.hasTrunk = hasTrunk;
		this.device = device;
	}

	public Car(Car car) {  //Special attention, this is the constructor that allows you to clone, not the previous one. That's why the object gps will clone also.
		super(car);
		this.hasTrunk = car.hasTrunk;
		this.device = car.device.clone();
	}

	@Override
	public Car clone() {
		return new Car(this);
	}

	public Device getDevice() { return device; }
}

class Bus extends Vehicle {
	private int seatsAmount;

	public Bus(String brand, int tires, int fuelCapacity, String color, int seatsAmount) {
		super(brand, tires, fuelCapacity, color);
		this.seatsAmount = seatsAmount;
	}

	public Bus(Bus bus) {
		super(bus);
		this.seatsAmount = bus.seatsAmount;
	}

	@Override
	public Bus clone() {
		return new Bus(this);
	}
}

class GpsDevice extends Device {
	private double height;
	private double width;

	public GpsDevice() {}

	public GpsDevice(double price, double height, double width) {
		super(price);
		this.height = height;
		this.width = width;
	}

	public GpsDevice(GpsDevice gpsDevice) {
		super(gpsDevice);
		this.height = gpsDevice.height;
		this.width = gpsDevice.width;
	}

	@Override
	public GpsDevice clone() {
		return new GpsDevice(this);
	}
}

abstract class Device {
	private double price;

	public Device() {}

	public Device(double price) {
		this.price = price;
	}

	protected Device(Device device) {
		this.price = device.price;
	}

	abstract public Device clone();
}
