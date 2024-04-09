/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;
import DataAccessLayer.UserDAO;

/**
 * Servlet implementation class RegistrationServlet
 * This servlet handles user registration.
 * Created April 3, 2024 3:51pm
 * @author Home
 */
public class RegistrationServlet extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String userType = request.getParameter("Users"); 

        // Basic validation for common fields
        if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("registrationError", "Email and password cannot be empty.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        // Validate email format
        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            request.setAttribute("registrationError", "Invalid email format.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

    UserDAO userDAO = new UserDAO();
    CredentialsDTO user = null;

    switch (userType) {
        case "retailer":
            user = new RetailersDTO();
            ((RetailersDTO)user).setRetailerName(request.getParameter("retailer_name"));
            break;
        case "consumer":
            user = new ConsumersDTO();
            ((ConsumersDTO)user).setFirstName(request.getParameter("first_name"));
            ((ConsumersDTO)user).setLastName(request.getParameter("last_name"));
            break;
        case "charitableOrg":
            user = new CharitableOrganizationDTO();
            ((CharitableOrganizationDTO)user).setCharitableOrgName(request.getParameter("charity_name"));
            break;
    }

    if (user != null) {
        user.setEmailAddress(request.getParameter("email"));
        user.setPassword(request.getParameter("password")); // Ensure this password is hashed for security.
        user.setLocation(request.getParameter("address"));
        user.setPhoneNumber(request.getParameter("phone_num"));
        
        //added and work
        user.setUserType(userType);
        

        
        
        boolean registrationSuccess = userDAO.addUser(user);

        if (registrationSuccess) {
            System.out.println("Registration successful for user: " + email); // Simple logging
            response.sendRedirect("Views/login.jsp"); // Redirect to login page on success.
        } else {
            System.out.println("Registration failed for user: " + email); // Simple logging
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("Views/registration.jsp").forward(request, response);
        }
    }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

    }
}
