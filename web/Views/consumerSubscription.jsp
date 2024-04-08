





<%-- 
    Document   : consumerSubsciption
    Created on : Apr 7, 2024, 9:49:27 a.m.
    Author     : Tom
--%>
<%@page import="java.util.*"%>
<%@page import="Model.Subscription"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Subscription Details</title>
</head>
<body>
    <h1>Subscription Details</h1>
    <%-- Debugging to check the session attribute --%>
Consumer ID from session: <%= session.getAttribute("user_id") %>

<a href="Views/consumerItems.jsp" >Consumer Item</a>
   <% List<Subscription> subscriptions = (List<Subscription>) session.getAttribute("subscriptions"); %>
   <% System.out.println("Number of subscriptions fetched from session: " + (subscriptions != null ? subscriptions.size() : "null")); %>
   <% System.out.println("Session ID in JSP: " + session.getId()); %>
<% if (subscriptions != null && !subscriptions.isEmpty()) { %>
    <% for (Subscription subscription : subscriptions) { %>
        <p>Location: <%= subscription.getLocation() %></p>
        <p>Retailer Name: <%= subscription.getRetailerName() %></p>
        <p>Email: <%= subscription.getEmail() %></p>
        <p>Phone Number: <%= subscription.getPhoneNumber() %></p>
        
        <form method="post" action="SubscriptionServlet">
            <input type="hidden" name="user_id" value="<%= session.getAttribute("user_id") %>">
            <input type="hidden" name="retailer_id" value="<%= subscription.getRetailerId() %>">
            <input type="hidden" name="action" value="unsubscribe">
            <button type="submit" class="btn btn-danger btn-spacing">Unsubscribe</button>
        </form>
        <hr>
    <% } %>
<% } else { %>
    <p>No subscription details found.</p>
<% } %>
</body>
</html>