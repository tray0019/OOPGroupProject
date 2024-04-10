/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author Mahsa
 */



import DataAccessLayer.DBConnection;
import org.junit.Test;
import java.sql.Connection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DBConnectionTest {

    @Test
    public void testConnection() {
        // Obtain the instance of DBConnection
        DBConnection dbConnection = DBConnection.getInstance();
        assertNotNull("DBConnection instance should not be null", dbConnection);

        // Obtain the database connection
        Connection connection = dbConnection.getConnection();
        assertNotNull("Database connection should not be null", connection);

        // Test if the connection is valid
        try {
            // The timeout is in seconds
            assertTrue("Connection should be valid", connection.isValid(5));
        } catch (Exception e) {
            e.printStackTrace();
            // Fail the test if any exception is thrown
            assertTrue("Connection validation failed due to an exception", false);
        }
    }
}
