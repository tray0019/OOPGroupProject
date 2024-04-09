/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Model.ItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import java.sql.DriverManager;

import java.sql.ResultSet;
/**
 * The RetailersDAO class allows retailer users to 
 * add item, select item, update item.
 * @author Tom
 * @author Modified by Yandom youmbi Farock 
 */
public class RetailersDAO implements ItemDAO{

        private Connection connection;
        ResultSet rs = null;
        int generatedItemId = -1; // Default value if no ID is generated
    
    public RetailersDAO(){
        connection = DBConnection.getInstance().getConnection();
    }
    
    
    @Override
    public void addItem(ItemDTO item, HttpSession session) {
        String insertQuery = "INSERT INTO inventory (user_id,item_name,quantity,price)VALUES(?,?,?,?)";
        
        int retailerId = (Integer) session.getAttribute("userId"); 
        
        try(PreparedStatement statement = connection.prepareStatement(insertQuery)){
            statement.setInt(1, retailerId);
            statement.setString(2, item.getItemName());
            statement.setInt(3, item.getItemQuantity());
            statement.setFloat(4, item.getPrice());
            
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
public  int addItemGood(ItemDTO item, int retailerId, int forConsumer, int forCharity) {
    String insertQuery = "INSERT INTO inventory (user_id, item_name, quantity, price, for_consumer, for_charity) VALUES (?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
        statement.setInt(1, retailerId);
        statement.setString(2, item.getItemName());
        statement.setInt(3, item.getItemQuantity());
        statement.setFloat(4, item.getPrice());
        statement.setInt(5, forConsumer);
        statement.setInt(6, forCharity);
        
        //statement.executeUpdate();
                    int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                // Get the generated keys (in this case, the auto-generated ID)
                rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    generatedItemId = rs.getInt(1); // Retrieve the generated ID
                }
            }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return generatedItemId;
}

    
    
    @Override
    public void selectItem() {
    
    }
    
    @Override
    public void deleteItem(int itemId){
            String deleteQuery = "DELETE FROM inventory WHERE inventory_id = ?";
    
    try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
        statement.setInt(1, itemId);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
      }
    }
        
    public void deleteItemGood(String itemName){
            String deleteQuery = "DELETE FROM inventory WHERE item_name = ?";
    
    try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
        statement.setString(1, itemName);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    //------------------------added by Vaishali---------------------------------------
    @Override
public List<ItemDTO> getAllAvailableItems() {
    List<ItemDTO> items = new ArrayList<>();
    String query =  "SELECT i.item_name, i.quantity, i.price, u.retailer_name, i.for_consumer, i.for_charity " +
                    "FROM inventory i " +
                    "JOIN users u ON i.user_id = u.user_id"; // Adjust as needed

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            ItemDTO item = new ItemDTO();
            item.setItemName(resultSet.getString("item_name"));
            item.setItemQuantity(resultSet.getInt("quantity"));
            item.setPrice(resultSet.getFloat("price"));
            item.setRetailerName(resultSet.getString("retailer_name"));
            item.setForConsumer(resultSet.getBoolean("for_consumer"));
            item.setForCharity(resultSet.getBoolean("for_charity"));
            
            // Add more fields as necessary
            items.add(item);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return items;
     
}
public List<ItemDTO> getRetailersAvailableItems(int userId){

    List<ItemDTO> items = new ArrayList<>();
    String query =  "SELECT i.item_name, i.quantity, i.price, u.retailer_name, i.for_consumer, i.for_charity " +
                    "FROM inventory i " +
                    "JOIN users u ON i.user_id = u.user_id " +
                    "WHERE i.user_id = ?"; // Filter by user_id
    
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            ItemDTO item = new ItemDTO();
            item.setItemName(resultSet.getString("item_name"));
            item.setItemQuantity(resultSet.getInt("quantity"));        
            item.setPrice(resultSet.getFloat("price"));     
            item.setRetailerName(resultSet.getString("retailer_name"));         
            item.setForConsumer(resultSet.getBoolean("for_consumer"));       
            item.setForCharity(resultSet.getBoolean("for_charity"));       
            
            // Add more fields as necessary
            items.add(item);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return items;
}
//------------------------------------------------------------------------------------   
}
