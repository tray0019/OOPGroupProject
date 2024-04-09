/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DataAccessLayer.CharityDAO;
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
 * Servlet implementation class CharityItemsServlet
 * This servlet handles requests related to fetching items available for charities.
 *
 * @author Vaishali
 */
@WebServlet(name = "ClaimItemServlet", urlPatterns = {"/ClaimItemServlet"})
public class ClaimItemServlet extends HttpServlet {

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
            out.println("<title>Servlet ClaimItemServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClaimItemServlet at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
        System.out.println("inside dopost method of claimitemservlet");
        HttpSession session = request.getSession();
        List<ItemDTO> cart = (List<ItemDTO>) session.getAttribute("cart");
        
        if (cart != null && !cart.isEmpty()) {
            CharityDAO charityDAO = new CharityDAO();
            System.out.println("before remove item from invontory of charity");
            charityDAO.removeItemsFromInventory(cart);
            session.removeAttribute("cart"); // Clear the cart after purchase
            request.setAttribute("claimSuccess", "Your claim has been confirmed!");
            response.sendRedirect("CharityItemsServlet"); // Change from forwarding to redirecting
        } else {
            request.setAttribute("error", "Your cart is empty.");
            request.getRequestDispatcher("Views/charityItems.jsp").forward(request, response);
        }
        
    }

}
