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
    @Override
    public void selectItem() {
    
    }
    
    public void deleteItem(){
        
    }

    @Override
    public void deleteItem(int itemId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //------------------------added by Vaishali---------------------------------------
    @Override
public List<ItemDTO> getAllAvailableItems() {
    List<ItemDTO> items = new ArrayList<>();
    String query =  "SELECT i.item_name, i.quantity, i.price, u.retailer_name, i.for_consumer, i.for_charity " +
                    "FROM inventory i " +
                    "JOIN users u ON i.user_id = u.user_id " +
                    "WHERE i.quantity > 0 " ;

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
