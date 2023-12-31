/* This approach is wrong because the Shelter class is open for modifications which violates the Single Responsibility and Open/Closed design principles*/

public class App {

    public static void main(String[] args) {
        Shelter shelter = new Shelter();
        Animal animal1 = shelter.createAnimal("cat");
        Animal animal2 = shelter.createAnimal("dog");
        animal1.makeSound();
        animal2.makeSound();
    }

}

class Shelter {

    public Animal createAnimal(String request) {
        Animal animal = null;
        if(request.equals("cat")) animal = new Cat();
        if(request.equals("dog")) animal = new Dog();
        //prepare for delivery to the new owner actions
        return animal;
    }

}

interface Animal {
    void makeSound();
}

class Cat implements Animal {

    @Override
    public void makeSound() { System.out.println("Meow meow"); }

}

class Dog implements Animal {

    @Override
    public void makeSound() { System.out.println("Woof woof"); }

}
