/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;
import Model.RetailersDTO;
import Model.CharitableOrganizationDTO;
import Model.CredentialsDTO;
import Model.ConsumersDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Tom
 */
public class UserDAO {
    private Connection connection;
    
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
                statement.setString(9, retailer.getBusinessName());
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
    
}
