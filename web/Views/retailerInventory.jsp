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
        <h1>Retail - Food Waste Reduction Platform</h1>
        <!-- Logout Link -->
        <div class="text-right mb-3">
            <a href="/OOPFinalProject_FWRP/LogoutServlet" class="btn btn-danger">Logout</a>
        </div>
        
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
            <a href="Views/addItem.jsp" class="btn btn-primary">Add Item</a>

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
    <!-- Update button -->
    <form action="UpdateItemServlet" method="get" style="display:inline;">
        <input type="hidden" name="item_name" value="<%= item.getItemName() %>">
        <input type="hidden" name="quantity" value="<%= item.getItemQuantity() %>">
        <input type="hidden" name="price" value="<%= item.getPrice() %>">
        <input type="hidden" name="availability" value="<%= item.isForConsumer()%>">
        <button type="submit" class="btn btn-success">Update</button>
    </form>
    
    <!-- Delete button -->
    <form action="DeleteItemServlet" method="POST" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this item?');">
        <input type="hidden" name="inventory_id" value="<%= item.getItemName() %>" />
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
