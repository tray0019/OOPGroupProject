/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Model.ItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsumerDAO {
    
    private final Connection connection;
    
    public ConsumerDAO() {
        // Initialize the connection using the singleton DBConnection instance
        connection = DBConnection.getInstance().getConnection();
    }
    
    public List<ItemDTO> getAllAvailableItemsForConsumer() {
        List<ItemDTO> items = new ArrayList<>();
        String query = "SELECT Inventory_id, item_name, quantity, price " +
                       "FROM Inventory WHERE for_consumer = 1 AND quantity >0";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ItemDTO item = new ItemDTO();                
            
                item.setItemId(resultSet.getInt("inventory_id"));
                System.out.println("inventory id:" + item.getItemId());
                item.setItemName(resultSet.getString("item_name"));
                System.out.println("item name :" + item.getItemName());
                item.setItemQuantity(resultSet.getInt("quantity"));
                System.out.println("quantity:" + item.getItemQuantity());
                item.setPrice(resultSet.getFloat("price"));
                System.out.println("price :" + item.getPrice());
                // Set additional properties as needed based on your ItemDTO class
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling
        }
        return items;
    }
    
    // Fetch an individual item by its ID
    public ItemDTO getItemById(int itemId) {
        ItemDTO item = null;
        String query = "SELECT inventory_id,item_name, quantity, price, for_consumer " +
                       "FROM Inventory WHERE inventory_id = ? AND for_consumer = 1 and quantity > 0";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, itemId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                
                item = new ItemDTO();
                // Assuming ItemDTO has a setter for inventory_id; if not, you may ignore this line
                item.setItemId(resultSet.getInt("inventory_id"));             
                item.setItemName(resultSet.getString("item_name"));         
                item.setItemQuantity(resultSet.getInt("quantity"));             
                item.setPrice(resultSet.getFloat("price"));              
                
                // Add other item properties as needed
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling
        }
        return item;
    }      
    
    // remove purchased item from the inventory table
    public void removeItemsFromInventory(List<ItemDTO> purchasedItems) {
    String query = "UPDATE Inventory SET quantity = quantity - ? WHERE inventory_id = ? AND for_consumer = 1";
    System.out.println("inside consumer DAO of remove items from inventory method");
        try {
            connection.setAutoCommit(false); // Start transaction
            PreparedStatement statement = connection.prepareStatement(query);
            for (ItemDTO item : purchasedItems) {
                if(item.getItemQuantity() > 0) {
                    System.out.println("inside remove items from inventory method");
                    statement.setInt(1, item.getItemQuantity());
                    statement.setInt(2, item.getItemId());
                    statement.executeUpdate();
                }
            }
            connection.commit(); // Commit transaction
        } catch (SQLException e) {
        try {
            connection.rollback(); // Rollback in case of error
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
        } finally {
        try {
            connection.setAutoCommit(true); // Reset auto-commit to true
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

}
