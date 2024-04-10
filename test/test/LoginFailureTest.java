package test;

import DataAccessLayer.UserDAO;
import Model.CredentialsDTO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNull;

public class LoginFailureTest {

    private UserDAO userDAO;

    @Before
    public void setup() {
        // Initialize UserDAO. This setup could be enhanced with mock objects.
        userDAO = new UserDAO();
    }

 

    @Test
    public void testLoginFailureIncorrectPassword() {
        // Test login failure with incorrect password
        CredentialsDTO user = userDAO.authenticateUser("mahsa@gmail.com", "incorrectPassword");
        assertNull("User should be null when incorrect credentials are provided.", user);
    }

    @Test
    public void testLoginFailureNonExistentEmail() {
        // Test login failure with a non-existent email
        CredentialsDTO user = userDAO.authenticateUser("nonexistentemail@example.com", "123456");
        assertNull("User should be null when a non-existent email is provided.", user);
    }

    @Test
    public void testLoginFailureNullEmail() {
        // Test login failure with null email
        CredentialsDTO user = userDAO.authenticateUser(null, "123456");
        assertNull("User should be null when email is null.", user);
    }

 

    @Test
    public void testLoginFailureEmptyCredentials() {
        // Test login failure with empty email and password
        CredentialsDTO user = userDAO.authenticateUser("", "");
        assertNull("User should be null when credentials are empty.", user);
    }
}
