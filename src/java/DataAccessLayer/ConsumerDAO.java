/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsumerDAO extends UserDAO {
    
    private final Connection connection;
    
    public ConsumerDAO() {
        // Initialize the connection using the singleton DBConnection instance
        connection = DBConnection.getInstance().getConnection();
    }
    
   






}
