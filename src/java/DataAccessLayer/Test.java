/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Model.*;
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
    /**
     * Test UserDAO
     */
    ConsumersDTO consumer = new ConsumersDTO();
    consumer.setEmailAddress("test@test.com");
    consumer.setLocation("194 Baker St. London");
    consumer.setPhoneNumber("613-222-4444");
    consumer.setPassword("Password123");
    consumer.setUserType("consumer");
    consumer.setFirstName("Sherlock");
    consumer.setLastName("Homy");
    
    /**
     * Test UserDAO for charity organization
     */
    CharitableOrganizationDTO charity = new CharitableOrganizationDTO();
    charity.setEmailAddress("greenCross@test.com");
    charity.setLocation("g5 Mureica st. Usa");
    charity.setPhoneNumber("613-888-9999");
    charity.setPassword("Mureca123");
    charity.setUserType("charitable_org.");
    charity.setCharitableOrgName("Green Cross");
    
    /**
     * Test UserDAO for Retailer
     */
    RetailersDTO retailer = new RetailersDTO();
    retailer.setEmailAddress("loblaws@test.com");
    retailer.setLocation("100 McArthur Ave., Ottawa, ON K1L 8H5");
    retailer.setPhoneNumber("613-444-6666");
    retailer.setPassword("Labla123");
    retailer.setUserType("retailer");
    retailer.setBusinessName("Loblaws");
  
    
    /**
     * Test work to insert user into data base
     */
    UserDAO user = new UserDAO();
    user.addUser(consumer);
    user.addUser(retailer);
    user.addUser(charity);
    
    /**
     * Testing RetailerDAO for adding item into the database
     */
    
    ItemDTO item = new ItemDTO();
    int userId = 2;
    item.setItemName("Mango");
    item.setItemQuantity(5);
    item.setPrice(5);
    
    item.setItemName("Banana");
    item.setItemQuantity(15);
    item.setPrice(5);
    
    ManualDAO retail = new ManualDAO();
    retail.addItem(item, userId);
    
    
    
    }
    
 
    
    
}

    
