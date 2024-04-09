/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DataAccessLayer.CharityDAO;
import Model.ItemDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Home
 */
@WebServlet(name = "CharityItemsServlet", urlPatterns = {"/CharityItemsServlet"})
public class CharityItemsServlet extends HttpServlet {

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
            out.println("<title>Servlet CharityItemsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CharityItemsServlet at " + request.getContextPath() + "</h1>");
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
        
        System.out.println("inside doget charity items  servlet");
        // Check for a success message in the session
        HttpSession session = request.getSession();
        String claimSuccess = (String) session.getAttribute("claimSuccess");
        if (claimSuccess != null) {
            request.setAttribute("claimSuccess", claimSuccess);
            session.removeAttribute("claimSuccess"); // Remove it so it's not displayed again after refresh
        }
        
        
        // Instantiate the DAO for charity
        CharityDAO charityDAO = new CharityDAO();

        // Fetch items available for charities
        List<ItemDTO> itemsForCharity = charityDAO.getAllAvailableItemsForUser();

        // Debugging: Print the list size to console
        System.out.println("Number of items available for charity: " + (itemsForCharity != null ? itemsForCharity.size() : "null"));

        // Set the fetched items as a request attribute for the JSP page
        request.setAttribute("itemsForCharity", itemsForCharity);

        // Forward the request to the JSP page that will display the items
        request.getRequestDispatcher("Views/charityItems.jsp").forward(request, response);     
        
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
