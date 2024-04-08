/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.ItemDTO;

import DataAccessLayer.*;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;



/**
 *
 * @author Home
 */
@WebServlet(name = "InventoryManagementServlet", urlPatterns = {"/InventoryManagementServlet"})
public class InventoryManagementServlet extends HttpServlet {
    private Connection connection;
    
    public InventoryManagementServlet(){
        connection = DBConnection.getInstance().getConnection();
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        RetailersDAO dao = new RetailersDAO();
//        List<ItemDTO> items = dao.getAllAvailableItems();
//        request.setAttribute("items", items);
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
//        
//        processRequest(request, response);
//    }
    
    /*
     *  Testing for displayin inventory in the dashboard
     */
   
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       
//        HttpSession session = request.getSession();
//        int userId = (int) session.getAttribute("userId");
//        
//        RetailersDAO dao = new RetailersDAO();
//        List<ItemDTO> items = dao.getRetailersAvailableItems(userId);
//     
//        request.setAttribute("items", items);
//        request.getRequestDispatcher("Views/retailerInventory.jsp").forward(request, response);
//     
//    }
//        
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ItemDTO> inventoryList = getInventoryItems();
        request.setAttribute("items", inventoryList);
        request.getRequestDispatcher("Views/retailerInventory.jsp").forward(request, response);
    }
    
        private List<ItemDTO> getInventoryItems() {
        List<ItemDTO> inventoryList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT i.item_name, i.quantity, i.price, u.retailer_name " +
                   "FROM Inventory i " +
                   "JOIN users u ON i.user_id = u.user_id AND u.users = 'retailer' "); //worked

            while (resultSet.next()) {
                ItemDTO item = new ItemDTO();
                item.setItemName(resultSet.getString("item_name"));
                item.setItemQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getFloat("price"));
                item.setRetailerName(resultSet.getString("retailer_name")); 
                inventoryList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryList;
    }
    
    


}
