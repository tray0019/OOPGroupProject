/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DataAccessLayer.CharityDAO;
import DataAccessLayer.ConsumerDAO;
import Model.ItemDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "AddToCharityCartServlet", urlPatterns = {"/AddToCharityCartServlet"})
public class AddToCharityCartServlet extends HttpServlet {

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
            out.println("<title>Servlet AddToCharityCartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCharityCartServlet at " + request.getContextPath() + "</h1>");
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
        
        // Get selected item IDs from the request                
        String[] selectedItemIds = request.getParameterValues("itemId");
        
        // Check if at least one item is selected
        if (selectedItemIds == null || selectedItemIds.length == 0) {
            // Redirect back to consumerItems.jsp with an error message or notification
            request.setAttribute("errorMessage", "Please select at least one item to add to the cart.");
            request.getRequestDispatcher("/charityItems.jsp").forward(request, response);
            return;
        }
        
        HttpSession session = request.getSession();
        List<ItemDTO> cart = (List<ItemDTO>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }        
        
        CharityDAO charityDAO = new CharityDAO();
        
        // Add selected items to the cart
        for (String itemIdString : selectedItemIds) {
            int itemId = Integer.parseInt(itemIdString);
            ItemDTO item = charityDAO.getItemById(itemId); 
            cart.add(item);
        }
        
        // After adding items to the cart
System.out.println("Cart size: " + cart.size());
for (ItemDTO item : cart) {
    if(item != null) {
        System.out.println("Item added to cart: " + item.getItemName());
    } else {
        System.out.println("Null item detected in the cart.");
    }
}
        // Update cart in session
        session.setAttribute("cart", cart);
       
        // Optionally redirect to a cart view page or back to the items list
        response.sendRedirect("Views/viewCharityCart.jsp"); 
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
