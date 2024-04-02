<%-- 
    Document   : index
    Created on : Apr 1, 2024, 11:06:27 p.m.
    Author     : Home
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Food Waste Reduction Platform</title>
    <!-- Optional: Include Bootstrap for styling -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-3">
        <h1>Welcome to the Food Waste Reduction Platform</h1>
        <p>This platform is dedicated to reducing food waste by connecting food retailers with consumers and charitable organizations. By making surplus food available for purchase or donation, we aim to minimize waste and support those in need.</p>
        
        <p><strong>Who Benefits?</strong></p>
        <ul>
            <li><strong>Retailers:</strong> Reduce waste and connect with a broader audience.</li>
            <li><strong>Consumers:</strong> Access food at reduced prices and help reduce waste.</li>
            <li><strong>Charitable Organizations:</strong> Obtain surplus food to support your cause.</li>
        </ul>
        
        <div>
            <a href="Views/registration.jsp" class="btn btn-primary">Register Now</a>
            <a href="Views/login.jsp" class="btn btn-secondary">Login</a>
        </div>

        <!-- Static Example of Available Items -->
        <h2 class="mt-4">Available Items</h2>
        <p>Below are examples of items that may be available for selection:</p>
        <ul>
            <li>Apples (10kg) - Available for Charitable Organizations</li>
            <li>Bread (20 loaves) - Available for Purchase</li>
            <!-- Add more static items as examples -->
        </ul>
    </div>
</body>
</html>
