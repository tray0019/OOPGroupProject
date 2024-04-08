/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DataAccessLayer.RetailersDAO;
import Model.ItemDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;


/**
 *
 * @author natan
 */
@WebServlet(name = "AddItemServlet", urlPatterns = {"/AddItemServlet"})
public class AddItemServlet extends HttpServlet {

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
            out.println("<title>Servlet AddItemServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddItemServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        //processRequest(request, response);
 // Retrieve item data from the form
    String itemName = request.getParameter("itemName");
    int itemQuantity = Integer.parseInt(request.getParameter("quantity"));
    float price = Float.parseFloat(request.getParameter("price"));
    String availableFor = request.getParameter("availability"); // Assuming this is the name of the field in your form
    
    // Create an ItemDTO object
    ItemDTO newItem = new ItemDTO();
    newItem.setItemName(itemName);
    newItem.setItemQuantity(itemQuantity);
    newItem.setPrice(price);
    
    // Determine the values for 'for_consumer' and 'for_charity' based on the selected option
    int forConsumer = 0;
    int forCharity = 0;
    if (availableFor.equals("Consumers")) {
        forConsumer = 1;
    } else if (availableFor.equals("charitable")) {
        forCharity = 1;
    }
    
    // Obtain the session to get userId
    HttpSession session = request.getSession();
    int retailerId = (int) session.getAttribute("userId");
    
    // Insert the item using RetailersDAO
    RetailersDAO retailersDAO = new RetailersDAO();
    retailersDAO.addItemGood(newItem, retailerId, forConsumer, forCharity);
    
        // After adding the item, retrieve the updated list of items from the database
    List<ItemDTO> itemList = retailersDAO.getRetailersAvailableItems(retailerId); // Assuming you have a method to retrieve all items

    // Set the updated list of items as an attribute in the request or session
    request.setAttribute("items", itemList); // You can also use session.setAttribute() if needed

    // Redirect to the retailer inventory JSP page
    RequestDispatcher dispatcher = request.getRequestDispatcher("Views/retailerInventory.jsp");
    dispatcher.forward(request, response);
    
    // Redirect to inventory page after adding item
    //response.sendRedirect("Views/Inventory.jsp");
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
