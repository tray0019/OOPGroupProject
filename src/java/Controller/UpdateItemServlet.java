/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DataAccessLayer.RetailersDAO;
import Model.ItemDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author natan
 */
@WebServlet(name = "UpdateItemServlet", urlPatterns = {"/UpdateItemServlet"})
public class UpdateItemServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateItemServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateItemServlet at " + request.getContextPath() + "</h1>");
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
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Retrieve the item name from the request parameter
    String itemName = request.getParameter("item_name");

    // Retrieve the item from the database based on the item name
    RetailersDAO retailersDAO = new RetailersDAO();
    ItemDTO item = retailersDAO.getItemByName(itemName);

    // Pass the retrieved item to the update item JSP page
    request.setAttribute("items", item);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Views/updateItem.jsp");
    dispatcher.forward(request, response);
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
    // Retrieve updated item details from the form
    String itemName = request.getParameter("item_name");
    int itemQuantity = Integer.parseInt(request.getParameter("quantity"));
    float price = Float.parseFloat(request.getParameter("price"));
    //String availableFor = request.getParameter("availability");

    // Determine the values for 'for_consumer' and 'for_charity' based on the selected option
//    int forConsumer = 0;
//    int forCharity = 0;
//    if (availableFor.equals("Consumers")) {
//        forConsumer = 1;
//    } else if (availableFor.equals("charitable")) {
//        forCharity = 1;
//    }

    // Create a new ItemDTO object with the updated details
    ItemDTO updatedItem = new ItemDTO();
    updatedItem.setItemName(itemName);
    updatedItem.setItemQuantity(itemQuantity);
    updatedItem.setPrice(price);
//    updatedItem.setForConsumer(forConsumer == 1);
//    updatedItem.setForCharity(forCharity == 1);

    // Update the item in the database
    RetailersDAO retailersDAO = new RetailersDAO();
    retailersDAO.updateItem(updatedItem);
//
//    // Redirect the user back to the inventory page
//    response.sendRedirect("Views/addItem.jsp");
    // Determine the values for 'for_consumer' and 'for_charity' based on the selected option
    int forConsumer;
    int forCharity;
    if (price == 0) {
        forCharity = 1;
        forConsumer = 0;
    } else {
        forConsumer = 1;
        forCharity = 0;
    }
// Obtain the session to get userId
    HttpSession session = request.getSession();
    int retailerId = (int) session.getAttribute("user_id");
    
    // Insert the item using RetailersDAO
    //RetailersDAO retailersDAO = new RetailersDAO();
    retailersDAO.addItemGood(updatedItem, retailerId, forConsumer, forCharity);
    
        // After adding the item, retrieve the updated list of items from the database
    List<ItemDTO> itemList = retailersDAO.getRetailersAvailableItems(retailerId); 

    // Set the updated list of items as an attribute in the request or session
    request.setAttribute("items", itemList); 

    // Redirect to the retailer inventory JSP page
    RequestDispatcher dispatcher = request.getRequestDispatcher("Views/retailerInventory.jsp");
    dispatcher.forward(request, response);
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
