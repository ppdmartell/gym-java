/* This approach is basically the same and it's still wrong because the SimpleAnimalFactory class is open for modifications which violates the Open/Closed design principle */

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

	public Animal saveAnimal(String request) {
		SimpleAnimalFactory animalFactory = new SimpleAnimalFactory();
		Animal animal = animalFactory.createAnimal(request);
		//Actions here to get ready the animal to deliver to the new owner
		return animal;
	}

}

class SimpleAnimalFactory {

	public Animal createAnimal(String request) {
		Animal animal = null;
		if(request.equals("cat")) animal = new Cat();
		if(request.equals("dog")) animal = new Dog();
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
