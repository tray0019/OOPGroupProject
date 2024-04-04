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
    <title>Food Waste Reduction Platform</title>
    <title>Login</title>
    <!-- Bootstrap CSS for styling and layout -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 40px;
        }
        .container {
            max-width: 400px;
            margin: 0 auto;
        }
        .form-group {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Food Waste Reduction Platform</h1>
        <h2 class="text-center mb-4">Login</h2>
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
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">Login</button>
            </div>
        </form>
        <div class="text-center mt-4">
            <p>Don't have an account? <a href="registration.jsp">Register here</a>.</p>
        </div>
        <p>Don't have an account? <a href="Views/registration.jsp">Register here</a>.</p>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
