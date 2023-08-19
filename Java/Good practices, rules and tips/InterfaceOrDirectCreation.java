/*
Sometimes, being List an interface and ArrayList a class, we think this next two lines are
technically the same:

List<Object> list1 = new ArrayList<>();
ArrayList<Object> list2 = new ArrayList<>();

But they are actually not, because if the ArrayList class has a static method, list1 wouldn't
be able to call it (compiling-time error) while list2 would.
*/

class InterfaceOrDirectCreation {
    public static void main(String[] args) {
        Device tv1 = new TvDevice();
        TvDevice tv2 = new TvDevice();

        tv1.turnOn();
        tv2.turnOn();

        tv1.readInstructions();  //This line will give a compiling-time error.
        tv2.readInstructions();
    }
}

interface Device {
    void turnOn();
}

class TvDevice implements Device {
    @Override
    public void turnOn() { System.out.println("Turning on the device."); }

    public static void readInstructions() { System.out.println("Reading instructions of the TV."); }
}
