/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import javax.servlet.http.HttpSession;
import DataAccessLayer.ConsumerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;
import java.util.List;

/**
 *
 * @author Home
 */
@WebServlet(name = "SubscriptionServlet", urlPatterns = {"/SubscriptionServlet"})
public class SubscriptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        if (userId != null) {
            ConsumerDAO dao = new ConsumerDAO();
            List<Subscription> subscriptions = dao.getSubscriptionsByConsumerId(userId);
            session.setAttribute("subscriptions", subscriptions);
            
            System.out.println("Session ID in servlet: " + request.getSession().getId());
            System.out.println("Subscriptions set in session: " + subscriptions.size());
            System.out.println("Fetched " + subscriptions.size() + " subscriptions for consumer ID " + userId);

            // Forward the fetched subscriptions to the JSP
            request.setAttribute("subscriptions", subscriptions);
            request.getRequestDispatcher("Views/consumerSubscription.jsp").forward(request, response);
        } else {
            response.sendRedirect("Views/login.jsp"); // Or appropriate login page
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parse request parameters
        int consumerId = Integer.parseInt(request.getParameter("user_id"));
        int retailerId = Integer.parseInt(request.getParameter("retailer_id"));
        String action = request.getParameter("action");

        // DAO initialization
        ConsumerDAO dao = new ConsumerDAO();

        // Check if the retailer exists
        RetailersDTO retailer = dao.getRetailerById(retailerId);
        if (retailer != null) {
            // Prepare subscription details
            String location = retailer.getLocation();
            String retailerName = retailer.getRetailerName();

            // Subscription/unsubscription logic
            if ("subscribe".equals(action) && !dao.isSubscribed(consumerId, retailerId)) {
                dao.subscribeToRetailer(consumerId, retailerId, location, retailerName);
            } else if ("unsubscribe".equals(action) && dao.isSubscribed(consumerId, retailerId)) {
                dao.unsubscribeFromRetailer(consumerId, retailerId);
            }

            // Update session with the latest subscription list
            // After updating subscriptions in doPost
            List<Subscription> subscriptions = dao.getSubscriptionsByConsumerId(consumerId);
            HttpSession session = request.getSession();
            request.setAttribute("subscriptions", subscriptions); // For immediate use
            request.getRequestDispatcher("Views/consumerSubscription.jsp").forward(request, response); // Forward, not redirect
            session.setAttribute("subscriptions", subscriptions);
            System.out.println("Fetched " + subscriptions.size() + " subscriptions for consumer ID " + consumerId);
            System.out.println("Session ID in servlet: " + request.getSession().getId());
            System.out.println("Subscriptions set in session: " + subscriptions.size());
            // Assuming you're setting the session attribute somewhere after this
            
            

            // Debug output
            System.out.println("Consumer ID: " + consumerId);
            System.out.println("Retailer ID: " + retailerId);
            System.out.println("Number of subscriptions set in session: " + (subscriptions != null ? subscriptions.size() : "null"));

        } else {
            System.out.println("Retailer not found for ID: " + retailerId);
            // Handle error appropriately
            // e.g., set an error message in the session or request and redirect to an error page or back to the subscription page
        }
    }

}
