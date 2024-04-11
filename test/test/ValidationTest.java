package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
public class ValidationTest {

    public String password, email;

    @BeforeEach
    public void initial() {
        password = "12345";
        email = "mahsa@gmail.com";
    }

    @Test
    public void validationPassword() {
        assertNotNull(password);
        assertNotEquals(password, "");
    }

    @Test
    public void validationEmail() {
        assertNotNull(email);
        assertNotEquals(email, "");
        assertTrue(email.matches("^[A-Za-z0-9+_.-]+@(.+)$"));
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ValidationTest.class);
        for (Failure failure : result.getFailures()) {
            System.err.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed successfully.");
        }
    }

}
