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
 *
 * @author Home
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

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
            out.println("<title>Servlet RegistrationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrationServlet at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // Basic server-side validation example
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    if(email == null || email.isEmpty() || password == null || password.isEmpty()) {
        request.setAttribute("errorMessage", "Email and Password are required.");
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
        return;
    }    

    String userType = request.getParameter("userType"); // Assuming you have a form field to capture this.
    CredentialsDTO user = null;

    switch (userType) {
        case "retailer":
            user = new RetailersDTO();
            ((RetailersDTO)user).setBusinessName(request.getParameter("businessName"));
            break;
        case "consumer":
            user = new ConsumersDTO();
            ((ConsumersDTO)user).setFirstName(request.getParameter("firstName"));
            ((ConsumersDTO)user).setLastName(request.getParameter("lastName"));
            break;
        case "charitableOrg":
            user = new CharitableOrganizationDTO();
            ((CharitableOrganizationDTO)user).setCharitableOrgName(request.getParameter("charitableOrgName"));
            break;
    }

    if (user != null) {
        user.setEmailAddress(request.getParameter("email"));
        user.setPassword(request.getParameter("password")); // Ensure this password is hashed for security.
        user.setLocation(request.getParameter("location"));
        user.setPhoneNumber(request.getParameter("phoneNum"));
        // Assume there's a method in CredentialsDTO to set userType or manage it accordingly.

        UserDAO userDAO = new UserDAO();
        boolean registrationSuccess = userDAO.addUser(user);

        if (registrationSuccess) {
            System.out.println("Registration successful for user: " + email); // Simple logging
            response.sendRedirect("login.jsp"); // Redirect to login page on success.
        } else {
            System.out.println("Registration failed for user: " + email); // Simple logging
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        }
    }

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
