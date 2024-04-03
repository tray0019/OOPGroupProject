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

import DataAccessLayer.UserDAO;
import Model.CredentialsDTO;

/**
 *
 * @author Home
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

  

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

        UserDAO userDAO = new UserDAO();
        CredentialsDTO user = userDAO.authenticateUser(email, password);

        if (user != null) {
            System.out.println("Login successful for user: " + email); // Simple logging
            request.getSession().setAttribute("user", user); // Store user in session.
            response.sendRedirect("Views/dashboard.jsp"); // Redirect to the dashboard.
        } else {
            System.out.println("Login failed for user: " + email); // Simple logging
            request.setAttribute("loginError", "Invalid email or password.");
            request.getRequestDispatcher("Views/login.jsp").forward(request, response);
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
