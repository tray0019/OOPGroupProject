/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;


import java.io.PrintWriter;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.ItemDTO;

import DataAccessLayer.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InventoryManagementServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InventoryManagementServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
   
     @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ItemDTO> inventoryList = getInventoryItems();
        request.setAttribute("inventoryList", inventoryList);
        request.getRequestDispatcher("Views/retailerInventoryJSP.jsp").forward(request, response);
    }
    
        private List<ItemDTO> getInventoryItems() {
        List<ItemDTO> inventoryList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Inventory ORDER BY item_name ASC");

            while (resultSet.next()) {
                ItemDTO item = new ItemDTO();
                item.setItemName(resultSet.getString("item_name"));
                item.setItemQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getFloat("price"));
                inventoryList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryList;
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
   

}
