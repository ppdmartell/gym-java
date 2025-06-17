class Dog extends Mammal {

    public Dog(String type, String size, double weight) {
        super(type, size, weight);
    }

    /*
    @Override
    public void move(String speed) {
        if ("slow".equals(this.getType())) {
            System.out.println(this.getExplicitType() + " walking");
        } else {
            System.out.println(this.getExplicitType() + " running");
        }
    }
    // This move(String speed) method can be commented since it was overriden on Mammal
    // However, if uncommented, this will be the implementation used instead of the Mammal's.
    */

    @Override
    public void makeNoise() {
        if ("Wolf".equals(this.getType())) {
            System.out.println("Howling ");
        } else {
            System.out.println("Woof ");
        }
    }

    @Override
    public void shedHair() {
        System.out.println(getExplicitType() + " shed hair all the time");
    }

}
