import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    public void depositShouldIncreaseBalance() {
        BankAccount account = new BankAccount();
        account.deposit(100);
        assertEquals(100, account.getBalance());
    }

    @Test
    public void multipleDepositsShouldAccumulate() {
        BankAccount account = new BankAccount();
        account.deposit(50);
        account.deposit(150);
        assertEquals(200, account.getBalance());
    }

    @Test
    public void withdrawShouldDecreaseBalance() {
        BankAccount account = new BankAccount();
        account.deposit(200);
        account.withdraw(75);
        account.withdraw(75);
        assertEquals(50, account.getBalance());
    }

    @Test
    public void withdrawingTooMuchShouldThrow() {
        BankAccount account = new BankAccount();
        account.deposit(50);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(100));
    }

    @Test
    public void negativeDepositShouldThrow() {
        BankAccount account = new BankAccount();
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-75));
    }

    @Test
    public void zeroDepositShouldThrow() {
        BankAccount account = new BankAccount();
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
    }
}
