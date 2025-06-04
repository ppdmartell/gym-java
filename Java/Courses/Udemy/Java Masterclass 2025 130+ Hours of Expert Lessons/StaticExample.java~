class StaticExample {

    public static void main(String[] args) {
        Dog rex = new Dog("Rex");
        rex.describeDog();
    }

}

class Dog {

    private static String name;

    public Dog(String name) {
        Dog.name = name;                              //Use the class name when the field is static. Still works with this though.
    }

    public void describeDog() {
        System.out.print("name: " + Dog.name);          //idem to previous comment.
    }

}
