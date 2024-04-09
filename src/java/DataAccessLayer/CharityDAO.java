/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;
import java.sql.Connection;

/**
 * The CharityDAO class allows charity users to 
 * add item, select item and subscribe.
 * @author Tom
 */
public class CharityDAO extends UserDAO{
    private Connection connection;
    
    public CharityDAO(){
        this.connection = DBConnection.getInstance().getConnection();
    }
    
 

    
}
