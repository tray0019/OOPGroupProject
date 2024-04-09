<%-- 
    Document   : charityItems
    Created on : Apr 6, 2024, 10:43:21 p.m.
    Author     : Vaishali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.ItemDTO"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Items Available for Donation</title>
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
        
        <% 
            String claimSuccess = (String) request.getAttribute("claimSuccess");
            if(claimSuccess != null && !claimSuccess.isEmpty()) {
        %>
        <div class="alert alert-success" role="alert">
            <%= claimSuccess %>
        </div>
        <% 
            }
        %>
        
        <h1>Welcome to the Food Waste Reduction Platform - Charities</h1>
        <!-- Logout Link -->
        <div class="text-right mb-3">
            <a href="LogoutServlet" class="btn btn-danger">Logout</a>
        </div>
        <h3>Items Available for Donation</h3>
        
        <% 
            String errorMessage = (String) request.getAttribute("error");
            if(errorMessage != null && !errorMessage.isEmpty()) {
        %>
            <div class="alert alert-danger" role="alert">
                <%= errorMessage %>
            </div>
        <% 
            }
        %>
    </div>
    
    <div class="container mt-3">
        <form action="AddToCharityCartServlet" method="post" onsubmit="return validateForm()">
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Item Name</th>
                        <th>Quantity</th>
                        <th>Claim for Donation</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("itemsForCharity");
                    if(items != null && !items.isEmpty()) {
                    System.out.println("item DTO has item for charity");
                        for (ItemDTO item : items) {
                    %>
                        <tr>
                            <td><%= item.getItemName() %></td>
                            <td><%= item.getItemQuantity() %></td>
                            <td>
                                <input type="checkbox" name="itemId" value="<%= item.getItemId() %>">
                            </td>
                        </tr>
                    <% 
                        }
                    } else { 
                    %>
                        <tr>
                            <td colspan="4" class="text-center">No items available for donation.</td>
                        </tr>
                    <% 
                    }
                    %>
                </tbody>
            </table>
            <div class="text-center">
                <button type="submit" class="btn btn-primary btn-spacing">Add Selected Items to Cart</button>
            </div>
        </form>
    </div>
    
    <script>
        function validateForm() {
            var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');                     
            
            if (checkboxes.length === 0) {
                alert('Please select at least one item to claim for donation.');
                return false;
            }
            return true;
        }
    </script>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>