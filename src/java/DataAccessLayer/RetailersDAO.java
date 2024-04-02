/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Model.ItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
/**
 * The RetailersDAO class allows retailer users to 
 * add item, select item, update item.
 * @author Tom
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

    
}
