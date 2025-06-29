class Main {

    public static void main(String[] args) {
       LambdaEmpty le = () -> System.out.println("Hello World!");
       le.show();

       LambdaAB lab = (int a, int b) -> System.out.println("a: " + a + ", b: " + b);
       lab.show(5, 15);

       LambdaReturnInt lri = a -> a + 10;     // In this case, the type "(int a)" was not provided since the method helps with this.
       int i = lri.provide(100);
       System.out.println("provided: " + i);
    }

}

interface LambdaEmpty {
    void show();
}

interface LambdaAB {
    void show(int a, int b);
}

interface LambdaReturnInt {
    int provide(int a);
}


// double number = 1.4;
//
// why if we could pass a behavior? Like:
//
// behvior = void doSomething() {
//      System.out.println("Hello World!");
// }
//
// In principle, we have an interface, with one method (abstract), and this by design turns the
// interface into a functional one (@FunctionalInterface).
