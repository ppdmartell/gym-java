//This is an example of a program implementing the Abstract Factory Pattern, using GPU ad monitors as example.
//https://www.youtube.com/watch?v=QNpwWkdFvgQ

public class App {
    public static void main(String[] args) {
        Manufacturer msiMan = new MsiManufacturer();
        Gpu msiGpu = msiMan.assembleGpu();
        Monitor msiMonitor = msiMan.assembleMonitor();

        Manufacturer asusMan = new AsusManufacturer();
        Gpu asusGpu = asusMan.assembleGpu();
        Monitor asusMonitor = asusMan.assembleMonitor();

        System.out.println("----------------------------------------------------------");
        msiGpu.mineCrypto();
        msiMonitor.display();
        System.out.println("----------------------------------------------------------");
        asusGpu.mineCrypto();
        asusMonitor.display();
        System.out.println("----------------------------------------------------------");
    }
}

abstract class Manufacturer {

    //Business logic (can) goes here.

    public abstract Gpu assembleGpu();
    public abstract Monitor assembleMonitor();

}

class MsiManufacturer extends Manufacturer {
    @Override
    public Gpu assembleGpu() { return new MsiGpu(); }

    @Override
    public Monitor assembleMonitor() { return new MsiMonitor(); }
}

class AsusManufacturer extends Manufacturer {
    @Override
    public Gpu assembleGpu() { return new AsusGpu(); }

    @Override
    public Monitor assembleMonitor() { return new AsusMonitor(); }
}

interface Gpu {
    void mineCrypto();
}

class MsiGpu implements Gpu {
    @Override
    public void mineCrypto() {
        System.out.println("Mining crypto from my MSI graphic card.");
    }
}

class AsusGpu implements Gpu {
    @Override
    public void mineCrypto() {
        System.out.println("Mining crypto from my ASUS graphic card.");
    }
}

interface Monitor {
    void display();
}

class MsiMonitor implements Monitor {
    @Override
    public void display() {
        System.out.println("Displaying a documentary, this is an MSI monitor.");
    }
}

class AsusMonitor implements Monitor {
    @Override
    public void display() {
        System.out.println("Displaying a documentary, this is an ASUS monitor.");
    }
}



/* The NVIDIA case

What happens if you add NVIDIA as a manufacturer but this company doesn't manufacture a Monitor object?
You MUST override every abstract method, but it you just simply throw a new exception while overriding the method
because this company doesn't manufacture monitors, then you would be violating the Liskov Substitution Principle.
What to do in this case?

*/

class NvidiaManufacturer extends Manufacturer {
    @Override
    public Gpu assembleGpu() { return new NvidiaGpu(); }

    @Override
    public Monitor assembleMonitor() {
        return null;                          //There is a problem here!!! How can this be solved?
    }
}

class NvidiaGpu implements Gpu {
    @Override
    public void mineCrypto() {
        System.out.println("Mining crypto from my NVIDIA graphic card.");
    }
}
