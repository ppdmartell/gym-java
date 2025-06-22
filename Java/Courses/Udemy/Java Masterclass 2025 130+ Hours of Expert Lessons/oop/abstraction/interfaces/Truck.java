class Truck implements Trackable {

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + ": Coordinates tracked.");
        System.out.println("Accessing the Trackable interface field from the object instance: " + KM_TO_MILES);
        // Even though you can access static fields through an instance, you never should. Proper way should
        // be Trackable.KM_TO_MILES
    }

    @Override
    public void load() {
        System.out.println(getClass().getSimpleName() + ": Cargo loaded.");
    }

    /*
     * Even when Truck only implements Trackable, it must Override method load(), because this method
     * is part of interface Loadable, which Trackable interface extends. So Truck has to comply with
     * the full contract (both interfaces), even when only implements on, Trackable.
     *
     * Otherwise, you will get the error:
     * .\Truck.java:1: error: Truck is not abstract and does not override abstract method load() in Loadable
     * class Truck implements Trackable {
     * ^
     * 1 error
     * */

}
