/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.dto.*;
/**
 * The CharityDAO class allows charity users to 
 * add item, select item and subscribe.
 * @author Tom
 */
public class CharityDAO implements ItemDAO{

    private Connection connection;
    public CharityDAO(){
        this.connection = DBConnection.getInstance().getConnection();
    }
    
     @Override
    public void addItem(ItemDTO item) {
        String insertQuery = "INSERT INTO inventory (item_name,quantity,price)VALUES(?,?,?)";
        
        try(PreparedStatement statement = connection.prepareStatement(insertQuery)){
            statement.setString(1, item.getItemName());
            statement.setFloat(2, item.getItemId());
            
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void selectItem() {
    
    }

    @Override
    public void subscribe() {
      
    }
    
}
