/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;
import DataAccessLayer.UserDAO;

/**
 * worked
 * @author Home
 */
public class RegistrationServlet extends HttpServlet {

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
UserDAO userDAO = new UserDAO();
    // Basic server-side validation example
    String email = request.getParameter("email");
    //String password = request.getParameter("password"); not use
  

    String userType = request.getParameter("Users"); // Assuming you have a form field to capture this.
    
    
    
    CredentialsDTO user = null;

    switch (userType) {
        case "retailer":
            user = new RetailersDTO();
            ((RetailersDTO)user).setBusinessName(request.getParameter("retailer_name"));
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
//        //user.setLocation(request.getParameter("location"));
//        //user.setPhoneNumber(request.getParameter("phoneNum"));
        
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

 
}
