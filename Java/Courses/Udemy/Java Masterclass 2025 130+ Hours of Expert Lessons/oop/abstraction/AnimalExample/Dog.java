class Dog extends Animal {

    public Dog(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        if ("slow".equals(this.getType())) {
            System.out.println(this.getType() + " walking");
        } else {
            System.out.println(this.getType() + " running");
        }
    }

    @Override
    public void makeNoise() {
        if ("Wolf".equals(this.getType())) {
            System.out.println("Howling ");
        } else {
            System.out.println("Woof ");
        }
    }

}
