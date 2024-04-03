<%-- 
    Document   : login
    Created on : Apr 1, 2024,10:50am
    Author     : Vaishali Jaiswal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <!-- Include Bootstrap for styling if you like -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Login</h2>
        <% if (request.getAttribute("loginError") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("loginError") %>
            </div>
        <% } %>
        <form action="/OOPFinalProject_FWRP/LoginServlet" method="POST">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
        <p>Don't have an account? <a href="Views/registration.jsp">Register here</a>.</p>
    </div>
</body>
</html>
