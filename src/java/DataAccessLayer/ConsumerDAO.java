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
 * The ConsumerDAO class allows consumers users to 
 * add item, select item and subscribe.
 * @author Tom
 */
public class ConsumerDAO implements ItemDAO {
    private Connection connection;
    
    public ConsumerDAO(){
        connection = DBConnection.getInstance().getConnection();
    }
    
    @Override
    public void addItem(ItemDTO item, HttpSession session) {
        
    }

    @Override
    public void selectItem() {
            
    }

    public void subscribe(int retailerId,HttpSession session) {
      int consumerId = (Integer) session.getAttribute("consumerId");
      
      String insertSubscription = "INSERT INTO Subscriptions(subscriber_id, retailer_id)VALUES(?,?)";
      
      try(PreparedStatement statement = connection.prepareStatement(insertSubscription)){
          statement.setInt(1, consumerId);
          statement.setInt(2, retailerId);
          statement.executeUpdate();
          
      }catch(SQLException e){
          e.printStackTrace();
      }
    }
    
}
