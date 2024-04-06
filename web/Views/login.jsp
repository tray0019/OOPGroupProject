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
        <h3 class="text-center mb-4">Login</h3>
        <% if (request.getAttribute("loginError") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("loginError") %>
            </div>
        <% } %>
        <!<!-- Rustom: Change to my own file path! -->
        <form action="/OOPFinalProject_FWRP/LoginServlet" autocomplete="off" method="POST"onsubmit="return validateLoginForm()">
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
        
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    
    // Main function to validate the login form
    function validateLoginForm() {
        var email = document.getElementById('email').value;
        var password = document.getElementById('password').value;

        if (!validateEmail(email)) {
            alert("Please enter a valid email address.");
            return false;
        }

        if (!validatePassword(password)) {
            // Specific error messages are shown inside validatePassword
            return false;
        }

        // If all validations pass
        return true;
    }
    
    // Function to validate email format
    function validateEmail(email) {
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    // Function to validate password criteria
    function validatePassword(password) {
        if (password.length < 8) {
            alert("Password must be at least 8 characters long.");
            return false;
        }
        if (!/[A-Z]/.test(password)) {
            alert("Password must contain at least one uppercase letter.");
            return false;
        }
        if (!/[!@#$%^&*(),.?":{}|<>]/.test(password)) {
            alert("Password must contain at least one special character (!@#$%^&*(),.?\":{}|<>).");
            return false;
        }
        return true;
    }

</script>
    
</body>
</html>
