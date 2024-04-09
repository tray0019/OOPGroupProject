/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DataAccessLayer.*;
import Model.CredentialsDTO;

import javax.servlet.http.HttpSession;


/**
 *
 * @author Home
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
  
  public LoginServlet(){
     
      DBConnection.getInstance().getConnection();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // This should be hashed and compared.
        
        // Basic validation
        if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("loginError", "Email and password cannot be empty.");
            request.getRequestDispatcher("Views/login.jsp").forward(request, response);
            return;
        }

        // Additional email format validation
        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            request.setAttribute("loginError", "Invalid email format.");
            request.getRequestDispatcher("Views/login.jsp").forward(request, response);
            return;
        }
            
        UserDAO userDAO = new UserDAO();
        CredentialsDTO user = userDAO.authenticateUser(email, password);
System.out.println("Setting userId in session: " + user.getUserId());
        if (user != null) {
        System.out.println("Login successful for user: " + email); // Simple logging
        
  System.out.println("Setting userId in session: " + user.getUserId());
//        session.setAttribute("user_id", user.getUserId()); // Use the user ID from the authenticated user object     
//        session.setAttribute("userType", user.getUserType()); // Store user type in session, if your User object has this field   
//        session.setAttribute("users", user); // Store the entire user object in the session, if you want to use it later
//        
        // Redirect based on user type
        HttpSession session;
            if ("retailer".equalsIgnoreCase(user.getUserType())) {
                response.sendRedirect("InventoryManagementServlet"); // Redirect to the retailer's dashboard
            } else if ("consumer".equalsIgnoreCase(user.getUserType())) {
                session = request.getSession();
                session.setAttribute("user_id", user.getUserId());  // Assuming 'getUserId()' correctly retrieves the user's ID
                response.sendRedirect("ConsumerItemsServlet");
            }else if ("charitable_org.".equalsIgnoreCase(user.getUserType())) {
                session = request.getSession();
                session.setAttribute("user_id", user.getUserId());  // Assuming 'getUserId()' correctly retrieves the user's ID
                response.sendRedirect("CharitableOrgItemsServlet");
            } else {
                // Handle other user types or default action
                response.sendRedirect("Views/index.jsp"); // Redirect to a default page or error page
            }
        
        
    } else {
        System.out.println("Login failed for user: " + email); // Simple logging
        request.setAttribute("loginError", "Invalid email or password.");
        request.getRequestDispatcher("Views/login.jsp").forward(request, response);
        }        
    } 
    
}
