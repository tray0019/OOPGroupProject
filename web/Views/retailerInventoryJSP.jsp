<%-- 
    Document   : inventoryJSP
    Created on : Mar 22, 2024, 4:02:27 p.m.
    Author     : Tom
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.ItemDTO"%>

<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Retailer Inventory</title>
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
                    <th>Actions</th>
                </tr>
            </thead>
            <a href="addItem.jsp" class="btn btn-primary">Add Item</a><br>
            <tbody>
                <%
                    // You would retrieve the list of items from a method that fetches data from the database.
                    List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("items");
                    if(items != null) {
                        for(ItemDTO item : items) {
                %>
                <tr>
                    <td><%= item.getItemName() %></td>
                    <td><%= item.getRetailerName() %></td> <!-- Make sure you have a method getRetailerName() in your ItemDTO -->
                    <td><%= item.getItemQuantity() %></td>
                    <td>$<%= item.getPrice() %></td>
                    <td><%= item.isForConsumer() ? "Consumers" : "Charitable Organizations" %></td> <!-- Assuming you have a method isForConsumer() -->
                    
                    <td>
                <!-- Update and Delete buttons for each item -->
                <a href="updateItem.jsp?itemId=<%= item.getItemId() %>" class="btn btn-success">Update</a>
                <form action="deleteItemServlet" method="POST" style="display:inline;">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>" />
                    <button type="submit" class="btn btn-danger">Delete</button>
                    
                </form>
            </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6">No items available at the moment.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
            
    </div>
</body>
</html>
