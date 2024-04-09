/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DataAccessLayer.CharityDAO;
import Model.ItemDTO;
import Model.Subscription;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Home
 */
@WebServlet(name = "CharitableOrgItemsServlet", urlPatterns = {"/CharitableOrgItemsServlet"})
public class CharitableOrgItemsServlet extends HttpServlet {

   

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
        System.out.println("inside doget consumer itmes  servlet");



        // Check for a success message in the session
        HttpSession session = request.getSession();
        String purchaseSuccess = (String) session.getAttribute("purchaseSuccess");
        if (purchaseSuccess != null) {
            request.setAttribute("purchaseSuccess", purchaseSuccess);
            session.removeAttribute("purchaseSuccess"); // Remove it so it's not displayed again after refresh
        }
      
        // Instantiate the DAO
        CharityDAO charityDAO = new CharityDAO();
        
        // Fetch items available for consumers
        List<ItemDTO> itemsForCharity= charityDAO.getAllAvailableItemsForUser();
        
        // Debugging: Print the list size to console
        System.out.println("Number of items fetched: " + (itemsForCharity != null ? itemsForCharity.size() : "null"));
        
        // Set the fetched items as a request attribute for the JSP page
        request.setAttribute("itemsForConsumer", itemsForCharity);
        
        // Forward the request to the JSP page that will display the items
        request.getRequestDispatcher("Views/charityItems.jsp").forward(request, response);
    }
    
}
