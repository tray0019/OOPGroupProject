/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Model.ConsumersDTO;
import java.sql.Connection;

/**
 * TEST YOUR DATABASE CONNECTION IF IT WORK
 * @author Tom
 */
public class Test {
    
    //test-VJ1
    //test-VJ2
    
    
     
    public static void main(String[] args){
      
        Connection con = DBConnection.getInstance().getConnection();
    if(con != null){
    System.out.print("Database connection succesful!");
        }else{
        System.out.print("Wrong schema name!");
      }
    }
    
    /**
     * Test UserDAO
     */
    ConsumersDTO consumer = new ConsumersDTO();
    
    
    
    
}
