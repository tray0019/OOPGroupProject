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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet; // added by Vaishali
/**
 *
 * @author Tom
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
}
