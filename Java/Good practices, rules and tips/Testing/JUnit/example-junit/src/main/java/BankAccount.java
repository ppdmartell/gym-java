import exceptions.MinimumBalanceViolationException;
import exceptions.NegativeValueException;
import exceptions.NotEnoughMoneyException;
import exceptions.OutOfBoundsValue;
import exceptions.ZeroValueNotAllowed;

public class BankAccount {
    private volatile double balance;
    private final double minBalance;

    private volatile boolean isActive;

    public BankAccount(double balance, double minBalance) {
        this.balance = balance;
        this.minBalance = minBalance;
        this.isActive = true;
    }

    public double getBalance() { return balance; }
    public double getMinBalance() { return minBalance; }

    public synchronized double withdraw(double amount) throws OutOfBoundsValue,
                                                              ZeroValueNotAllowed,
                                                              NegativeValueException,
                                                              NotEnoughMoneyException {
        if (amount == 0) throw new ZeroValueNotAllowed("Money value to withdraw can't be zero.");
        if (amount < 0) throw new NegativeValueException("Money value to withdraw can't be negative.");
        if (amount > Double.MAX_VALUE) throw new OutOfBoundsValue(String.format("Value %f is out of bounds.", amount));
        if (amount > balance) throw new NotEnoughMoneyException("Not enough balance");
        if (balance - amount < minBalance) throw new MinimumBalanceViolationException("Not possible to withdraw the money, violates the minimum balance allowed.");
        balance -= amount;
        return balance;
    }

    public synchronized double deposit(double amount) throws NegativeValueException,
                                                             ZeroValueNotAllowed,
                                                             OutOfBoundsValue {
        if (amount < 0) throw new NegativeValueException("The amount to deposit can't be a negative number.");
        if (amount == 0) throw new ZeroValueNotAllowed("Zero is not a possible value to deposit.");
        if (amount > Double.MAX_VALUE) throw new OutOfBoundsValue(String.format("Value %f is out of bounds.", amount));
        try {
            Thread.sleep(0, 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return balance += amount;
    }

    public boolean isActive() { return isActive; }

    public synchronized void setActive(boolean state) {
        this.isActive = state;
    }
}
