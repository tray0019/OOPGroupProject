/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Model.ItemDTO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * This class is for adding the item to the 
 * database. This class is made so I don't have to
 * modify the retailer table.v
 * @author Tom
 */
public class ManualDAO {
    private Connection connection;
    
    public ManualDAO(){
        connection = DBConnection.getInstance().getConnection();
    }
    
    public void addItem(ItemDTO item, int retailId) {
        String insertQuery = "INSERT INTO inventory (user_id,item_name,quantity,price)VALUES(?,?,?,?)";
        
        try(PreparedStatement statement = connection.prepareStatement(insertQuery)){
            statement.setInt(1, retailId);
            statement.setString(2, item.getItemName());
            statement.setInt(3, item.getItemQuantity());
            statement.setFloat(4, item.getPrice());
            
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
