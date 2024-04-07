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
 *
 * @author Vaishali
 */


@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {              
        
        // Get selected item IDs from the request                
        String[] selectedItemIds = request.getParameterValues("itemId");
        
        // Check if at least one item is selected
        if (selectedItemIds == null || selectedItemIds.length == 0) {
            // Redirect back to consumerItems.jsp with an error message or notification
            request.setAttribute("errorMessage", "Please select at least one item to add to the cart.");
            request.getRequestDispatcher("/consumerItems.jsp").forward(request, response);
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
        response.sendRedirect("Views/viewCart.jsp"); 
    }
}
