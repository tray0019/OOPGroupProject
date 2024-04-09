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
import javax.servlet.http.HttpSession;

/**
 * The CharityDAO class allows charity users to 
 * add item, select item and subscribe.
 * @author Tom
 */
public class CharityDAO implements ItemDAO{
    
    //mm-modified
    private final Connection connection;
    //private Connection connection;
    
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

    @Override
    public void deleteItem(int itemId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ItemDTO> getAllAvailableItems() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // -------------------------code add by Vaishali-----------------------------
    
    public List<ItemDTO> getAllAvailableItemsForCharity() {
    List<ItemDTO> items = new ArrayList<>();
    String query = "SELECT inventory_id, item_name, quantity, price " +
                   "FROM Inventory WHERE for_charity = 1 AND quantity > 0";
    
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
                System.out.println("quantity :" + item.getItemQuantity());                
               
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
                       "FROM Inventory WHERE inventory_id = ? AND for_charity = 1 and quantity > 0";

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
                       
                // Add other item properties as needed
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling
        }
        return item;
    }      
    
    public void claimItems(List<ItemDTO> claimedItems) {
    String query = "UPDATE Inventory SET quantity = 0 WHERE inventory_id = ? AND for_charity = 1";
    
    System.out.println("inside consumer DAO of remove items from inventory method");
        try {
            connection.setAutoCommit(false); // Start transaction
            PreparedStatement statement = connection.prepareStatement(query);
            for (ItemDTO item : claimedItems) {
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
    
    // remove claimed item from the inventory table
    public void removeItemsFromInventory(List<ItemDTO> claimItems) {
        System.out.println("inside the charity remove items from inventory method");
    String query = "UPDATE Inventory SET quantity = quantity - ? WHERE inventory_id = ? AND for_charity = 1";
    System.out.println("inside charity DAO of remove items from inventory method");
        try {
            connection.setAutoCommit(false); // Start transaction
            PreparedStatement statement = connection.prepareStatement(query);
            for (ItemDTO item : claimItems) {
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

    //------------------------------------------------------
}
