package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Single database connection.
 * @author Tom - Rustom Trayvilla
 * @since 2024/03/24
 * @author Modified by Farock Yandom Youmbi Farock
 */
public class DBConnection {

    private static DBConnection instance;
    private final Connection connection;

    private DBConnection() {
        // Directly establish the connection using credentials
        String serveUrl = "jdbc:mysql://localhost:3306/fwrp";
        String userString = "root";
        String passwordString = "Farock4199.";
        String driveString = "com.mysql.cj.jdbc.Driver";

        try {
            // Register JDBC driver
            Class.forName(driveString);
            // Get connection
            connection = DriverManager.getConnection(serveUrl, userString, passwordString);
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
