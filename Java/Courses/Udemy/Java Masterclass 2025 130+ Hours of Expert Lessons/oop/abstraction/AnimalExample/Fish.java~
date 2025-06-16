class Fish extends Animal {

    public Fish(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        if ("slow".equals(this.getType())) {
            System.out.println(this.getExplicitType() + " lazily swimming");
        } else {
            System.out.println(this.getExplicitType() + " darting frantically");
        }
    }

    @Override
    public void makeNoise() {
        if ("Goldfish".equals(this.getType())) {
            System.out.println("swish ");
        } else {
            System.out.println("splash ");
        }
    }

}
