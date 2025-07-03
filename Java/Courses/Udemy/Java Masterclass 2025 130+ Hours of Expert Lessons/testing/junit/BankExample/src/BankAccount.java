public class BankAccount {

    private String name;
    private String lastName;
    private double balance = 0.0;

    public BankAccount() {}

    public BankAccount(String name, String lastName, double balance) {
        this.name = name;
        this.lastName = lastName;
        this.balance = balance;
    }

    public double deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Please, use a valid deposit value.");
        balance += amount;
        return balance;
    }

    public double withdraw(double amount) {
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds.");
        balance -= amount;
        return balance;
    }

    public double getBalance() { return balance; }

}
