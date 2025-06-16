public class Main {

    public static void main(String[] args) {
        Employee tim = new Employee("Tim", "11/11/1985", "01/01/2020");
        SalariedEmployee joe = new SalariedEmployee("Joe", "11/11/1990", "03/03/2020", 35000);
        SalariedEmployee karen = new SalariedEmployee("Karen", "11/11/1990", "03/03/2021", 25000);
        
        System.out.println(tim);
        System.out.println(joe);
        System.out.println(karen);

        System.out.println("Age = " + tim.getAge());
        System.out.println("Pay = " + tim.collectPay());

        System.out.println("Age = " + joe.getAge());
        System.out.println("Pay = " + joe.collectPay());
    
        System.out.println("Age = " + karen.getAge());
        System.out.println("Pay = " + karen.collectPay());
    
        System.out.println("Karen's pay check is: " + karen.collectPay());

        HourlyEmployee mary = new HourlyEmployee("Mary", "05/05/1970", "03/03/2021", 15);
        System.out.println(mary);
        System.out.println("Mary's paycheck = $" + mary.collectPay());
        System.out.println("Mary's holiday pay = $" + mary.getDoublePay());
    }

}
