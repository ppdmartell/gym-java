/* This is the Factory Method pattern, this way the code is close for modification, and the only code you should change is the one in the main method. It's amazing! */
//https://www.youtube.com/watch?v=EdFq_JIThqM

public class App {

    public static void main(String[] args) {
        Shelter shelterCat = new CatDepartment();
        Animal animalCat = shelterCat.saveAnimal();

        Shelter shelterDog = new DogDepartment();
        Animal animalDog = shelterDog.saveAnimal();

        animalCat.makeSound();
        animalDog.makeSound();
    }

    static interface Animal {
        void makeSound();
    }

    static class Cat implements Animal {
        @Override
        public void makeSound() { System.out.println("Meow"); }
    }

    static class Dog implements Animal {
        @Override
        public void makeSound() { System.out.println("Woof"); }
    }

    static abstract class Shelter {

        public Animal saveAnimal() {
            Animal animal = createAnimal();
            //Other actions to perform with the object animal, such as prepare it before delivery to the new owner
            return animal;
        }

        public abstract Animal createAnimal();
    }

    static class CatDepartment extends Shelter {
        @Override
        public Animal createAnimal() {
            return new Cat();
        }
    }

    static class DogDepartment extends Shelter {
        @Override
        public Animal createAnimal() {
            return new Dog();
        }
    }

}
