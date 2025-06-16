abstract class Animal {

    private String type;
    private String size;
    private double weight;

    public Animal(String type, String size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    protected String getType() { return type; }
    protected String getSize() { return size; }
    protected double weight() { return weight; }

    public abstract void move(String speed);
    public abstract void makeNoise();

}
