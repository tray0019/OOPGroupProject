/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;
import Model.RetailersDTO;
import Model.ConsumersDTO;
import Model.CredentialsDTO;
import Model.CharitableOrganizationDTO;
import Model.ItemDTO;
import Model.Subscription;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet; // added by Vaishali
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Tom
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class UserDAO {
    private final Connection connection;
    
    public UserDAO(){
        connection = DBConnection.getInstance().getConnection();
    }
    
    public boolean addUser(CredentialsDTO user){
        String insertUsers = "INSERT INTO Users(email,address,phone_num,password,Users,first_name,last_name,charity_name,retailer_name)VALUES(?,?,?,?,?,?,?,?,?) ";
        try(PreparedStatement statement = connection.prepareStatement(insertUsers)){
            statement.setString(1, user.getEmailAddress());
            statement.setString(2, user.getLocation());
            statement.setString(3, user.getPhoneNumber());//needs to be string
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getUserType());
            
            //missing userType, did not add User in the inserUsers
            
            if(user instanceof ConsumersDTO){
                ConsumersDTO consumer = (ConsumersDTO) user;
                statement.setString(6, consumer.getFirstName());
                statement.setString(7, consumer.getLastName());
            } else {
                statement.setString(6, null);
                statement.setString(7, null);
            }
            
            if(user instanceof CharitableOrganizationDTO){
                CharitableOrganizationDTO charity = (CharitableOrganizationDTO) user;
                statement.setString(8, charity.getCharitableOrgName());
            } else {
                statement.setString(8, null);
            }
            
            if(user instanceof RetailersDTO){
                RetailersDTO retailer = (RetailersDTO) user;
                statement.setString(9, retailer.getRetailerName());
            } else {
                statement.setString(9, null);
            }
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    //-----------------------------------------------------------------------added by Vaishali
    
    public CredentialsDTO authenticateUser(String email, String inputPassword) {
        // Assuming inputPassword will be hashed within this method or prior to calling it
        String query = "SELECT * FROM Users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                // Here you would hash the inputPassword and compare it with storedPassword
                // Let's assume you have a method `hashPassword` and `checkPassword`
                if (checkPassword(inputPassword, storedPassword)) {
                    // Instantiate the correct subclass of CredentialsDTO based on userType
                    String userType = resultSet.getString("Users");
                    System.out.println("UserType: " + userType);
                    CredentialsDTO user = null;
                    switch (userType) {
                        case "consumer":
                            user = new ConsumersDTO();
                            break;
                        case "retailer":
                            user = new RetailersDTO();
                            break;
                        case "charitable_org.":
                            user = new CharitableOrganizationDTO();
                            break;
                    }
                    if (user != null) {
                        user.setUserType(userType);
                        user.setEmailAddress(email);
                        user.setUserId(resultSet.getInt("user_id")); // Retrieve user ID

                        // Other fields as necessary
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Authentication failed
    }

    private boolean checkPassword(String inputPassword, String storedPassword) {
        // Here you should hash the inputPassword and compare it with the storedPassword
        // For now, this is a placeholder
        // In reality, use a hashing library like BCrypt
        return hashPassword(inputPassword).equals(storedPassword);
    }

    private String hashPassword(String password) {
        // Placeholder for hashing - Use BCrypt or similar in practice
        return password; // Do not use in production!
    }
   //------------------------------------------------------------------------------------------ 

 
    
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
    String query = "delete from Inventory WHERE inventory_id = ? AND for_consumer = 1";
    System.out.println("inside consumer DAO of remove items from inventory method");
        try {
            connection.setAutoCommit(false); // Start transaction
            PreparedStatement statement = connection.prepareStatement(query);
            for (ItemDTO item : purchasedItems) {
                if(item.getItemQuantity() > 0) {
                    System.out.println("inside remove items from inventory method");
                    
                    statement.setInt(1, item.getItemId());
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
    
    public boolean isSubscribed(int userID, int retailerId) {
    String query = "SELECT 1 FROM Subscriptions WHERE subscriber_id = ? AND retailer_id = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, userID);
        statement.setInt(2, retailerId);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();  // return true if there is at least one record
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    
    public boolean subscribeToRetailer(int userID, int retailerId, String location, String retailerName) {
    String query = "INSERT INTO Subscriptions (subscriber_id, retailer_id, location, retailer_name) VALUES (?, ?, ?, ?)";
     boolean success = false;
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, userID);
        statement.setInt(2, retailerId);
        statement.setString(3, location);
        statement.setString(4, retailerName);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } return success;
}

public boolean unsubscribeFromRetailer(int userID, int retailerId) {
    String query = "DELETE FROM Subscriptions WHERE subscriber_id = ? AND retailer_id = ?";
    boolean success = false;
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, userID);
        statement.setInt(2, retailerId);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }return success;
}


public RetailersDTO getRetailerById(int retailerId) {
    RetailersDTO retailer = null;
    String query = "SELECT * FROM Users WHERE user_id = ? AND Users = 'retailer'";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, retailerId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            retailer = new RetailersDTO();
            retailer.setUserId(resultSet.getInt("user_id"));
            retailer.setRetailerName(resultSet.getString("retailer_name"));
            retailer.setLocation(resultSet.getString("address"));  // Assuming the location is stored in the address field
            retailer.setEmailAddress(resultSet.getString("email"));
            retailer.setPhoneNumber(resultSet.getString("phone_num"));
            // set other fields as needed
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return retailer;
}

public List<Subscription> getSubscriptionsByUserId(int userID) {
    List<Subscription> subscriptions = new ArrayList<>();
    String query = "SELECT s.*, s.location, s.retailer_name, u.email, u.phone_num " +
                   "FROM subscriptions s " +
                   "JOIN users u ON s.retailer_id = u.user_id " +
                   "WHERE s.subscriber_id = ?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, userID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Subscription subscription = new Subscription();
            subscription.setRetailerId(resultSet.getInt("retailer_id"));
            subscription.setLocation(resultSet.getString("location"));
            subscription.setRetailerName(resultSet.getString("retailer_name"));
            subscription.setEmail(resultSet.getString("email"));
            subscription.setPhoneNumber(resultSet.getString("phone_num"));
            subscriptions.add(subscription);
        }

        // Debugging statement to log the number of subscriptions fetched
        System.out.println("Fetched " + subscriptions.size() + " subscriptions for consumer ID " + userID);

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return subscriptions;
}
}
