/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.ItemDTO;
import DataAccessLayer.ConsumerDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class AddToCartServlet
 * This servlet handles adding items to the user's cart.
 * Created April 4, 2024 3:51pm
 * @author Vaishali
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {
    
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
        String[] selectedItemIds = request.getParameterValues("inventory_id");//THIS ONE!!
        
        // Check if at least one item is selected
        if (selectedItemIds == null || selectedItemIds.length == 0) {
            // Redirect back to consumerItems.jsp with an error message or notification
            request.setAttribute("errorMessage", "Please select at least one item to add to the cart.");
            request.getRequestDispatcher("Views/consumerItems.jsp").forward(request, response);
            return;
        }
        
        HttpSession session = request.getSession();
        List<ItemDTO> cart = (List<ItemDTO>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }        
        
        ConsumerDAO consumerDAO = new ConsumerDAO();
        
        // Add selected items to the cart
        for (String itemIdString : selectedItemIds) {
            int itemId = Integer.parseInt(itemIdString);
            ItemDTO item = consumerDAO.getItemById(itemId); 
            if (item != null) {
            cart.add(item);
            System.out.println("Item added to cart: " + item.getItemName());
        } else {
            System.out.println("Attempted to add a null item to the cart for item ID: " + itemId);
        }
        }
        
         System.out.println("Cart size: " + cart.size());
    

    // Redirect to the cart view page
    response.sendRedirect("Views/viewCart.jsp"); 
  
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
        
    }
}
