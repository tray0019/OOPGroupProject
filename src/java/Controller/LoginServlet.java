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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DataAccessLayer.*;
import Model.CredentialsDTO;
import java.sql.ResultSet;

/**
 *
 * @author Home
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

  private Connection connection;
  
  public LoginServlet(){
      connection = DBConnection.getInstance().getConnection();
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


        if (authentication(email,password)) {
            System.out.println("Login successful for user: " + email); // Simple logging
            request.getSession().setAttribute("user", email); // Store user in session.
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
    
    /**
     * Added for user logs-in
     */
    private boolean authentication(String email, String password){
        String userPassword = "SELECT * FROM Users WHERE email = ? AND password = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(userPassword)){
            statement.setString(1, email);
            statement.setString(2, password);
            
           try(ResultSet resultSet = statement.executeQuery()){
              return resultSet.next(); 
           } 
            
        
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    

}
