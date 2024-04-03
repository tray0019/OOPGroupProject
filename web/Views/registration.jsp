<%-- 
    Document   : registration
    Created on : Apr 1, 2024, 10:51am
    Author     : Vaishali Jaiswal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <!-- Include Bootstrap for styling -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Registration</h2>
        <form action="/OOPFinalProject_FWRP/RegistrationServlet" method="POST">
            <div class="form-group">
                <label for="userType">I am a:</label>
                <select class="form-control" id="userType" name="userType" required>
                    <option value="consumer">Consumer</option>
                    <option value="retailer">Retailer</option>
                    <option value="charitableOrg">Charitable Organization</option>
                </select>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <!-- Add additional fields as necessary -->
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
</body>
</html>

