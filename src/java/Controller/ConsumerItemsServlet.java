/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DataAccessLayer.ConsumerDAO;
import Model.ItemDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ConsumerItemsServlet
 * This servlet handles requests related to fetching items available for consumers.
 *
 * @author Vaishali
 */
@WebServlet(name = "ConsumerItemsServlet", urlPatterns = {"/ConsumerItemsServlet"})
public class ConsumerItemsServlet extends HttpServlet {

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
            out.println("<title>Servlet ConsumerItemsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConsumerItemsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        System.out.println("inside doget consumer itmes  servlet");
        // Check for a success message in the session
        HttpSession session = request.getSession();
        String purchaseSuccess = (String) session.getAttribute("purchaseSuccess");
        if (purchaseSuccess != null) {
            request.setAttribute("purchaseSuccess", purchaseSuccess);
            session.removeAttribute("purchaseSuccess"); // Remove it so it's not displayed again after refresh
        }
        
        // Instantiate the DAO
        ConsumerDAO consumerDAO = new ConsumerDAO();
        
        // Fetch items available for consumers
        List<ItemDTO> itemsForConsumer = consumerDAO.getAllAvailableItemsForConsumer();
        
        // Debugging: Print the list size to console
        System.out.println("Number of items fetched: " + (itemsForConsumer != null ? itemsForConsumer.size() : "null"));
        // Set the fetched items as a request attribute for the JSP page
        request.setAttribute("itemsForConsumer", itemsForConsumer);
        
        // Forward the request to the JSP page that will display the items
        request.getRequestDispatcher("Views/consumerItems.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
