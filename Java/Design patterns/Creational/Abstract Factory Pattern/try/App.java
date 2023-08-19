/* This example uses the same Abstract Factory Pattern but with different entities involved.
   The special attention of this pattern should go into the stores and the two functionalities
   they both share, in this case the two methods for returning the same base-types.
*/

public class App {
    public static void main(String[] args) {
        Store york = new YorkStore();
        Bike mountainBike = york.prepare();
        Quad fuelQuad = york.assemble();

        Store montevideo = new MontevideoStore();
        Bike cityBike = montevideo.prepare();
        Quad electricQuad = montevideo.assemble();

        System.out.println("----------------------------------------------------------");
        mountainBike.ride();
        fuelQuad.move();
        System.out.println("----------------------------------------------------------");
        cityBike.ride();
        electricQuad.move();
        System.out.println("----------------------------------------------------------");
    }
}

interface Bike {
    void ride();
}

class MountainBike implements Bike {
    @Override
    public void ride() { System.out.println("Mountain bike is riding."); }
}

class CityBike implements Bike {
    @Override
    public void ride() { System.out.println("City bike is riding."); }
}

interface Quad {
    void move();
}

class FuelQuad implements Quad {
    @Override
    public void move() { System.out.println("Fuel quad is rolling!"); }
}

class ElectricQuad implements Quad {
    @Override
    public void move() { System.out.println("Electric quad is rolling!"); }
}

abstract class Store {
    //Business logic goes here

    abstract Bike prepare();
    abstract Quad assemble();
}

//York store sells Mountain bikes and Fuel quads
class YorkStore extends Store {
    @Override
    public Bike prepare() {
        return new MountainBike();
    }

    @Override
    public Quad assemble() {
        return new FuelQuad();
    }
}

//Montevideo store sells City bikes and Electric quads
class MontevideoStore extends Store {
    @Override
    public Bike prepare() {
        return new CityBike();
    }

    @Override
    public Quad assemble() {
        return new  ElectricQuad();
    }
}
