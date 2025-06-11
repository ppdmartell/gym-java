import cars.*;

class Main {

    public static void main(String[] args) {
       Car car = new Car("Murcielago");
       runRace(car);

       Car gasCar = new GasCar("Chevy", 13.97, 6);
       runRace(gasCar);

       Car electricCar = new ElectricCar("Tesla Model S", 34.7, 6);
       runRace(electricCar);

       Car hybridCar = new HybridCar("Ferrari SF90", 18.5, 18, 6);
       runRace(hybridCar);
    }

    public static void runRace(Car car) {
        car.startEngine();
        car.drive();
    }

}
