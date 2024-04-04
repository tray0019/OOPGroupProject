<%-- 
    Document   : dasjbpard
    Created on : Apr 1, 2024, 10:43:12 a.m.
    Author     : Home
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.ItemDTO"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-3">
        <h4>Available Items</h4>
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>Item Name</th>
                    <th>Retailer</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Available For</th>
                </tr>
            </thead>
            
            <tbody>
                <%
                    // You would retrieve the list of items from a method that fetches data from the database.
                    List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("inventoryList");
                    if(items != null) {
                        for(ItemDTO item : items) {
                %>
                <tr>
                    <td><%= item.getItemName() %></td>
                    <td><%= item.getRetailerName() %></td> <!-- Make sure you have a method getRetailerName() in your ItemDTO -->
                    <td><%= item.getItemQuantity() %></td>
                    <td>$<%= item.getPrice() %></td>
                    <td><%= item.isForConsumer() ? "Consumers" : "Charitable Organizations" %></td> <!-- Assuming you have a method isForConsumer() -->
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5">No items available at the moment.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>

