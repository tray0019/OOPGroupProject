/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DataAccessLayer.RetailersDAO;
import Model.ItemDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is responsible for handling requests to display items on the main page.
 * It interacts with the {@link RetailersDAO} to fetch a list of all available items and forwards them to be displayed on the index.jsp page.
 * It handles both GET and POST requests but primarily processes GET requests to fetch and display items.
 * 
 * Created April 3, 2024 3:51pm
 * @author Vaishali 
 */
public class DisplayItemsServlet extends HttpServlet {
    
    //private final static Logger LOGGER = Logger.getLogger(DisplayItemsServlet.class.getName());


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
            out.println("<title>Servlet DisplayItemsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DisplayItemsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    /**
     * Handles the HTTP GET method.
     * Fetches available items using {@link RetailersDAO} and forwards them to index.jsp for display.
     *
     * @param request  The HttpServletRequest object that contains the request the client made of the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs during the processing of the request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
                  
        RetailersDAO dao = new RetailersDAO();
        List<ItemDTO> items = dao.getAllAvailableItems();
                
        //Rustom: Change to my attributes name, Before: "items"
        request.setAttribute("items", items);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
    /**
     * Handles the HTTP POST method by calling the processRequest method.
     * In this implementation, POST requests are processed in the same way as GET requests.
     *
     * @param request  The HttpServletRequest object that contains the request the client made of the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs during the processing of the request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }  

}
