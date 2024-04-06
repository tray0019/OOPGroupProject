<%-- 
    Document   : index
    Created on : Apr 1, 2024, 11:06:27 p.m.
    Author     : Vaishali Jaiswal
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="Model.ItemDTO"%>
<%@page import="Controller.InventoryManagementServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Food Waste Reduction Platform</title>
    <!-- Bootstrap CSS for styling -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 20px;
        }
        table {
            margin-top: 20px;
            width: 100%;
        }
        .centered {
            margin-left: auto;
            margin-right: auto;
        }
        .mt-3 {
            margin-top: 1rem;
        }
        .btn-spacing {
            margin-right: 5px;
        }
        .table th, .table td {
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container text-center">
        <h1>Welcome to the Food Waste Reduction Platform</h1>
        <p>This platform is dedicated to reducing food waste by connecting food retailers with consumers and charitable organizations. By making surplus food available for purchase or donation, we aim to minimize waste and support those in need.</p>
        <p><strong>Who Benefits?</strong></p>
        <ul class="list-unstyled centered">
            <li><strong>Retailers:</strong> Reduce waste and connect with a broader audience.</li>
            <li><strong>Consumers:</strong> Access food at reduced prices and help reduce waste.</li>
            <li><strong>Charitable Organizations:</strong> Obtain surplus food to support your cause.</li>
        </ul>     
    </div>
    
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
        <div class="mt-3">
            <a href="Views/registration.jsp" class="btn btn-primary btn-spacing">Register Now</a>
            <a href="Views/login.jsp" class="btn btn-secondary">Login</a>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script>
window.onload = function() {
    document.getElementById('registrationForm').reset();
};
</script>
    
</body> 
</html>
