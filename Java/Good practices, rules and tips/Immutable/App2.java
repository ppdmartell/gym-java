/*
This is a bad example that might lead a developer to think a class is immutable just because
it doesn't have setters and the fields are private. By the way, Date type is mutable, so
BE CAREFUL when using an instance field of type Date!

Resources:
[1] https://www.youtube.com/watch?v=qbNMAJvv7qI
[2] https://www.youtube.com/watch?v=i5eZHUDbMiA
*/

class App2 {
    public static void main(String[] args) {
        C2 c2 = new C2();
        c2.value = 100;
        C1 c1 = new C1(99, c2);
        System.out.println(c1);  //Should print c2.value = 100
        c2.value = 999;             //Changing c2.value to 999
        System.out.println(c1);  //Prints 999 (as expected) while seems like it would print c1.c2.value as 100 
    }
}

class C2 {
    Integer value;
}

class C1 {
    private int x;
    private C2 c2;

    public C1(int x, C2 c2) {
        this.x = x;
        this.c2 = c2;
    }

    public C2 getC2() {  return c2; }

    public int getX() { return x; }

    @Override
    public String toString() {
        return "x: " + x + ", c2: " + c2.value;
    }
}
