/*
Exceptions are a mechanism for handling issues that could come up with the execution of a program
but that, if handled, won't stop the execution of this. On the other hand, Errors can not be handled
and they will stop the execution of your program. There are two types of exceptions: checked and
unchecked. And they can occur at run-time or compile-time.

Resources:
[1] https://www.youtube.com/watch?v=8WTVLa1Xtsk&list=PLsyeobzWxl7rS9B2K1l--VDpCn41gijnV
*/

class App {
    public static void main(String[] args) {

        System.out.println("----------------Unchecked exception----------------");
        int a = 33, b = 4;
        try {
            int c = a / b;
            System.out.println(c);
        } catch (ArithmeticException e) {
            System.out.println("Division by zero is undefined.");
        } catch (Exception e) { //Exception is the root of all exceptions, so it should go at the very bottom.
            System.out.println("Something happened, we just don't know what.");
        } finally {
            System.out.println("This is the finally block, will be executed no matter what when using a try-catch block.");
        }

        System.out.println("----------------Checked exception----------------");
        try {
            int result = divide(1,0);
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Division by zero is undefined.");
        } catch (Exception e) { //Exception is the root of all exceptions, so it should go at the very bottom.
            System.out.println("Something happened, we just don't know what.");
        } finally {
            System.out.println("This is the finally block, will be executed no matter what when using a try-catch block.");
        }
    }

    /*
    This method is the source of the checked exception. Basically doesn't care about using
    try-catch blocks at all, but by "throws ArithmeticException" forces the developer to
    implement a mechanism of try-catch block of code when calling this method.
    */
    private static int divide(int a, int b) throws ArithmeticException {
        return a / b;
    }
}
