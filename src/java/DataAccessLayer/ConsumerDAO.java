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
    
   

public List<ItemDTO> getAllAvailableItemsForUser() {
        
        List<ItemDTO> items = new ArrayList<>();
        String query = "SELECT i.inventory_id, i.item_name, i.quantity, i.price, i.user_id AS retailer_id, u.retailer_name " +
                   "FROM Inventory i " +
                   "JOIN Users u ON i.user_id = u.user_id " +
                   "WHERE u.Users = 'retailer' AND for_consumer = 1 and price > 0";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ItemDTO item = new ItemDTO();       
                System.out.println("Retailer ID for item: " + item.getRetailerId());

            
                item.setItemId(resultSet.getInt("inventory_id"));
            item.setItemName(resultSet.getString("item_name"));
            item.setItemQuantity(resultSet.getInt("quantity"));
            item.setPrice(resultSet.getFloat("price"));
            item.setRetailerId(resultSet.getInt("retailer_id"));
            item.setRetailerName(resultSet.getString("retailer_name")); // Set retailer name
            items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling
        }
        return items;
    }




}
