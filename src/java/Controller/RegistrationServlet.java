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
 * @author Vaishali Jaiswal
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

   /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract common parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String userType = request.getParameter("Users"); // This should be the name attribute in the select tag

        UserDAO userDAO = new UserDAO();
        boolean registrationSuccess;

        // Create a DTO based on the user type
        switch (userType) {
            case "retailer":
                RetailersDTO retailer = new RetailersDTO();
                retailer.setBusinessName(request.getParameter("retailer_name"));
                retailer.setEmailAddress(email);
                retailer.setPassword(password);
                retailer.setLocation(address);
                retailer.setPhoneNumber(phone);
                retailer.setUserType(userType);
                registrationSuccess = userDAO.addUser(retailer);
                break;
            case "consumer":
                ConsumersDTO consumer = new ConsumersDTO();
                consumer.setFirstName(request.getParameter("first_name"));
                consumer.setLastName(request.getParameter("last_name"));
                consumer.setEmailAddress(email);
                consumer.setPassword(password);
                consumer.setLocation(address);
                consumer.setPhoneNumber(phone);
                consumer.setUserType(userType);
                registrationSuccess = userDAO.addUser(consumer);
                break;
            case "charitable_org":
                CharitableOrganizationDTO charity = new CharitableOrganizationDTO();
                charity.setCharitableOrgName(request.getParameter("charity_name"));
                charity.setEmailAddress(email);
                charity.setPassword(password);
                charity.setLocation(address);
                charity.setPhoneNumber(phone);
                charity.setUserType(userType);
                registrationSuccess = userDAO.addUser(charity);
                break;
            default:
                registrationSuccess = false;
                request.setAttribute("errorMessage", "Invalid user type.");
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
                return;
        }

        // Redirect or forward based on the registration success
        if (registrationSuccess) {
            response.sendRedirect("login.jsp"); // Redirect to login page on success
        } else {
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        }
    }
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
