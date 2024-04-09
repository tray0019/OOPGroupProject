<%-- 
    Document   : addItem.jsp
    Created on : Apr 8, 2024, 5:16:11 a.m.
    Author     : natan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Item</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-3">
        <h1>Add New Item</h1>
        
        <form action="/OOPFinalProject_FWRP/AddItemServlet" method="post">
            <div class="form-group">
                <label for="itemName">Item Name</label>
                <input type="text" class="form-control" id="itemName" name="itemName" required>
            </div>
            <div class="form-group">
                <label for="quantity">Quantity</label>
                <input type="number" class="form-control" id="quantity" name="quantity" required>
            </div>
            <div class="form-group">
                <label for="price">Price in CAD</label>
                <input type="number" class="form-control" id="price" name="price" step="0.01" required>
            </div>
            <div class="form-group">
                <label for="availability">Available For</label>
                <select class="form-control" id="availability" name="availability" required>
                    <option value="consumers">Consumers</option>
                    <option value="charitable">Charitable Organizations</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add Item</button>
        </form><br>
        
            <a href="javascript:history.go(-1);" class="btn btn-secondary">Cancel</a>

    </div>
</body>
</html>

