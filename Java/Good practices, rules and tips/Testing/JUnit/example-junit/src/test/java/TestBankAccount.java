/*
Difference between Assertion and Assumption[3] is:
- If an Assertion is true:   test succeeds
- If an Assertion is false:  test fails

- If an Assumption is true:  test is run
- If an Assumption is false: test is aborted

@Order: This annotation allows you to execute the tests in a customized order instead of
the default top-bottom order.

Resources:
[1] https://www.linkedin.com/learning/java-testing-with-junit-14267963/write-and-run-test-classes-and-methods?resume=false
[2] https://www.linkedin.com/learning/java-testing-with-junit-14267963/assertions?resume=false
[3] https://www.linkedin.com/learning/java-testing-with-junit-14267963/assumptions?resume=false
*/

import exceptions.NegativeValueException;
import exceptions.NotEnoughMoneyException;
import exceptions.OutOfBoundsValue;
import exceptions.MinimumBalanceViolationException;
import exceptions.ZeroValueNotAllowed;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

@DisplayName("Test BankAccount class assertions")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)   //This annotation is SUPER IMPORTANT, otherwise the order of test execution won't work.
public class TestBankAccount {
    private BankAccount bankAccount;
    @Test
    @Order(5)
    @DisplayName("Withdraw 50.0")
    public void testWithdraw() {
        bankAccount = new BankAccount(100d, 1d);
        assertEquals(50d, bankAccount.withdraw(50d));
    }

    @Test
    @Order(10)
    @DisplayName("Block withdraw if not enough money.")
    public void testWithdrawInsufficient() {
        bankAccount = new BankAccount(100d, 1d);
        assertThrows(NotEnoughMoneyException.class, () -> bankAccount.withdraw(101d));
    }

    @Test
    @Order(9)
    @DisplayName("Don't allow negative value for withdrawal.")
    public void testWithdrawNegative() {
        bankAccount = new BankAccount(100d, 1d);
        assertThrows(NegativeValueException.class, () -> bankAccount.withdraw(-1d));
    }

    @Test
    @Order(8)
    @DisplayName("Handle upper bound for double types.")
    public void testWithdrawOutOfBoundsValue() {
        bankAccount = new BankAccount(100d, 1d);
        String largeValue = "1.7976931348623158E309"; //This value is bigger than DOUBLE.MAX_VALUE
        double value = new BigDecimal(largeValue).doubleValue();
        assertThrows(OutOfBoundsValue.class, () -> bankAccount.withdraw(value));
        //assertThrows(OutOfBoundsValue.class, () -> bankAccount.withdraw(Double.MIN_VALUE - 1d));
    }

    @Test
    @Order(7)
    @DisplayName("Don't allow value zero.")
    public void testWithZeroValueNotAllowed() {
        bankAccount = new BankAccount(100d, 1d);
        assertThrows(ZeroValueNotAllowed.class, () -> bankAccount.withdraw(0));
    }

    @Test
    @Order(6)
    @DisplayName("Don't allow withdrawal if below min balance threshold.")
    public void testWithdrawViolatingMinimumBalance() {
        bankAccount = new BankAccount(100d, 50d);
        assertThrows(MinimumBalanceViolationException.class, () -> bankAccount.withdraw(60d));
    }

    //////////////////////////////////////////////deposit()/////////////////////////////////////////////////////
    @Test
    @Order(3)
    @DisplayName("Deposit 100.0")
    public void testDeposit() {
        bankAccount = new BankAccount(100d, 1d);
        assertEquals(150d, bankAccount.deposit(50d));
    }

    @Test
    @Order(2)
    @DisplayName("Don't allow depositing negative value.")
    public void testDepositNegative() {
        bankAccount = new BankAccount(100d, 1d);
        assertThrows(NegativeValueException.class, () -> bankAccount.deposit(-1d));
    }

    @Test
    @Order(4)
    @DisplayName("Handle upper bound for double types.")
    public void testDepositOutOfBoundsValue() {
        bankAccount = new BankAccount(100d, 1d);
        String largeValue = "1.7976931348623158E309"; //This value is bigger than DOUBLE.MAX_VALUE
        double value = new BigDecimal(largeValue).doubleValue();
        assertThrows(OutOfBoundsValue.class, () -> bankAccount.deposit(value));
    }

    @Test
    @Order(1)
    @DisplayName("Don't allow value zero as a deposit.")
    public void testDepositZeroValue() {
        bankAccount = new BankAccount(100d, 1d);
        assertThrows(ZeroValueNotAllowed.class, () -> bankAccount.deposit(0d));
    }

    @Test
    @Order(11)
    @DisplayName("Perform the operation so fast that it happens under a nanosecond.")
    public void testDepositSpeed() {
        bankAccount = new BankAccount(200d, 1d);
        assertTimeout(Duration.ofNanos(1), () -> bankAccount.deposit(17d));  //It will fail because I put a Thread.sleep(0, 2) for 2 nanoseconds on purpose. See [2](13:33).
    }
}
