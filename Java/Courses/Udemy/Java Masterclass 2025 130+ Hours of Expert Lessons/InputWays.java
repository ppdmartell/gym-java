import java.util.Scanner;

class InputWays {

    public static void main(String[] args) {
       String name = System.console().readLine("Please, enter your name: "); //console() is disabled when used in IDEs, so expect error there.
       
       Scanner sc = new Scanner(System.in);
       System.out.print("Please, enter your age: ");
       int age = sc.nextInt();

       System.out.println("name: " + name + " and age:" + age);
    }

}
