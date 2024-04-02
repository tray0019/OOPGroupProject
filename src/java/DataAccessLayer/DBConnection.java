/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *  Single database connection.
 * @author Tom - Rustom Trayvilla
 * @since 2024/03/24
 */
public class DBConnection {
    
    private static DBConnection instance;
    private Connection connection;
    
    private static String serveUrl = "jdbc:mysql://localhost:3306/fwrp";
    private static String userString ="root";
    private static String passwordString = "";
    private static String driveString = "com.mysql.cj.jdbc.Driver";
    
    private DBConnection(){
        try {
            this.connection = DriverManager.getConnection(serveUrl,userString,passwordString);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static DBConnection getInstance(){
        if(instance == null){
            instance = new DBConnection();
        }return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
}
