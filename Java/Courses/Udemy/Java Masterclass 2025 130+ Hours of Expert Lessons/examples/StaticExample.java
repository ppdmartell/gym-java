class StaticExample {

    public static void main(String[] args) {
        Dog rex = new Dog("Rex");
        Dog fluffy =  new Dog("Fluffy");
        rex.describeDog();
        fluffy.describeDog();

        //both will show Fluffy as name since static variables are shared among instances.
    }

}

class Dog {

    private static String name;

    public Dog(String name) {
        Dog.name = name;                              //Use the class name when the field is static. Still works with this though.
    }

    public void describeDog() {
        System.out.println("name: " + name);
    }

}
