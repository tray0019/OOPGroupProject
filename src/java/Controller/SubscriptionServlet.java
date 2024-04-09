/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import javax.servlet.http.HttpSession;
import DataAccessLayer.UserDAO;
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
            UserDAO dao = new UserDAO();
            List<Subscription> subscriptions = dao.getSubscriptionsByUserId(userId);
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
        int consumerId = Integer.parseInt(request.getParameter("user_id"));
        int retailerId = Integer.parseInt(request.getParameter("retailer_id"));
        String action = request.getParameter("action");

        if (retailerId <= 0) {
            System.out.println("Invalid retailer ID received: " + retailerId);
            response.sendRedirect("Views/errorPage.jsp");
            return;
        }

        UserDAO dao = new UserDAO();
        RetailersDTO retailer = dao.getRetailerById(retailerId);

        if (retailer != null) {
            boolean success = false;

            if ("subscribe".equals(action) && !dao.isSubscribed(consumerId, retailerId)) {
                success = dao.subscribeToRetailer(consumerId, retailerId, retailer.getLocation(), retailer.getRetailerName());
            } else if ("unsubscribe".equals(action) && dao.isSubscribed(consumerId, retailerId)) {
                success = dao.unsubscribeFromRetailer(consumerId, retailerId);
            }

            if (success) {
                System.out.println("Action " + action + " successful for retailer ID: " + retailerId);
            } else {
                System.out.println("Action " + action + " failed for retailer ID: " + retailerId);
            }

            if ("subscribe".equals(action)) {
                response.sendRedirect("ConsumerItemsServlet");
            } else if ("unsubscribe".equals(action)) {
                response.sendRedirect("SubscriptionServlet");
            }

        } else {
            System.out.println("Retailer not found for ID: " + retailerId);
            response.sendRedirect("Views/errorPage.jsp");
        }
    }

}
