/*
Resources:
[1] https://www.linkedin.com/learning/java-testing-with-junit-14267963/your-first-test-hellojunit?resume=false
*/

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestCode {
    @Test
    public void testSayHello() {
        Code code = new Code();
        assertEquals("Hello world!", code.sayHello());
    }
}
