/*
Assumptions will avoid or allow the following tests to be executed or aborted.
*/

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("Test BankAccount class assumptions")
public class TestBankAccountAssumptions {

    BankAccount bankAccount;

    @Test
    @DisplayName("Test activation account after creation.")
    public void testActive() {
        bankAccount = new BankAccount(100d, 1d);
        assumeTrue(bankAccount != null);      //In this case bankAccount is always != null (could be == null and the test will be ignored)
                                                        // so it will always be asserted. But that's an example of using an assumption.
        assertTrue(bankAccount.isActive());

        //assumingThat(bankAccount != null, () -> assertTrue(bankAccount.isActive()));     //This is the same as the first two statement but in a one-liner.
    }
}
