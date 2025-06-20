abstract class Mammal extends Animal {

    public Mammal(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        System.out.println(getExplicitType() + " ");
        System.out.println("slow".equals(speed) ? "walks (mammal)" : "run (mammal)");
    }

    public abstract void shedHair();

}

// An abstract class than extends another abstract class doesn't have to implement abstract methods
//  - It can implement all of the parent's abstract methods.
//  - It can implement some of them.
//  - It can implement none of them.
//  - It can also include additional abstract methods, which will force subclasses to implement both
//  Animal's abstract methods as well as Mammal's.
