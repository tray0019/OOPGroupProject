package test;

import java.sql.Connection;
import DataAccessLayer.DBConnection;
import DataAccessLayer.UserDAO;
import Model.CredentialsDTO;
//import com.sun.jdi.connect.spi.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

//import static org.junit.Assert.*;


@RunWith(JUnitPlatform.class)
public class LoginTest {

    public UserDAO userDAO;

    @BeforeEach
    public void initial() {
        userDAO = new UserDAO();
    }

    @Test
    public void loginTest() {
        CredentialsDTO user = userDAO.authenticateUser("mahsa@gmail.com", "123456");
        assertNotNull(user);
        assertEquals("mahsa@gmail.com", user.getEmailAddress());
    }

    public static void main(String[] args) {
        Connection con= DBConnection.getInstance().getConnection();
        Result result = JUnitCore.runClasses(LoginTest.class);
        for (Failure failure : result.getFailures()) {
            System.err.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed successfully.");
        }
    }

}
