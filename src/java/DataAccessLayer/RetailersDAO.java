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

import java.sql.ResultSet;
/**
 * The RetailersDAO class allows retailer users to 
 * add item, select item, update item.
 * @author Tom
 */
public class RetailersDAO implements ItemDAO{

    private final Connection connection;
    
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

    @Override
    public List<ItemDTO> getAllAvailableItems() {
        List<ItemDTO> items = new ArrayList<>();
        String query = "SELECT * FROM inventory WHERE quantity > 0"; // Adjust as needed

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                ItemDTO item = new ItemDTO();
                item.setItemId(resultSet.getInt("inventory_id"));
                item.setItemName(resultSet.getString("item_name"));
                item.setItemQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getFloat("price"));
                // Add more fields as necessary
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
        
}
