/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;
import Model.dto.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

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
    
    /*
    * This method is wrong since charity does not add item to the database 
    * but to its own!
    */
     @Override
    public void addItem(ItemDTO item, HttpSession session) {
        
    }

    @Override
    public void selectItem() {
    
    }

    public void subscribe(int retailerId, HttpSession session) {
      int charityId = (Integer) session.getAttribute("charityId");
      
      String insertSubscription = "INSERT INTO Subscriptions(subscriber_id, retailer_id)VALUES(?,?)";
      
      try(PreparedStatement statement = connection.prepareStatement(insertSubscription)){
          statement.setInt(1, charityId);
          statement.setInt(2, retailerId);
          statement.executeUpdate();
          
      }catch(SQLException e){
          e.printStackTrace();
      }
      
    }
    
}
