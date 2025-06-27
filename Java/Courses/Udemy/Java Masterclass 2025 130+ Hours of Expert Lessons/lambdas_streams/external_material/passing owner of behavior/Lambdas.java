class Lambdas {

    public static void main(String[] args) {
        Greeting greeting = new HelloGreeter();
        greeter(greeting);
    }

    private static void greeter(Greeting greeting) {
        greeting.perform();
    }

}
