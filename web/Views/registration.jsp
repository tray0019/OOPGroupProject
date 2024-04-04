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
    <script type="text/javascript">
        function updateFormFields(userType) {
            var retailerFields = document.getElementById('retailerFields');
            var consumerFields = document.getElementById('consumerFields');
            var charityFields = document.getElementById('charityFields');

            retailerFields.style.display = 'none';
            consumerFields.style.display = 'none';
            charityFields.style.display = 'none';

            if (userType === 'retailer') {
                retailerFields.style.display = 'block';
            } else if (userType === 'consumer') {
                consumerFields.style.display = 'block';
            } else if (userType === 'charitable_org.') {
                charityFields.style.display = 'block';
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Registration</h2>
        <form action="/OOPFinalProject_FWRP/RegistrationServlet" method="POST">
            <div class="form-group">
                <label for="userType">I am a:</label>
                <select id="userType" name="Users" class="form-control" onchange="updateFormFields(this.value)">
                    <option value="retailer">Retailer</option>
                    <option value="consumer">Consumer</option>
                    <option value="charitable_org.">Charitable Organization</option>
                </select>
            </div>

            <!-- Retailer Fields -->
            <div id="retailerFields" style="display: none;">
                <div class="form-group">
                    <label for="retailerName">Retailer Name:</label>
                    <input type="text" id="retailerName" name="retailer_name" class="form-control">
                </div>
                <!-- Additional retailer-specific fields here -->
            </div>

            <!-- Consumer Fields -->
            <div id="consumerFields" style="display: none;">
                <div class="form-group">
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="first_name" class="form-control">
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" name="last_name" class="form-control">
                </div>
                <!-- Additional consumer-specific fields here -->
            </div>

            <!-- Charity Fields -->
            <div id="charityFields" style="display: none;">
                <div class="form-group">
                    <label for="charityName">Charitable Organization Name:</label>
                    <input type="text" id="charityName" name="charity_name" class="form-control">
                </div>
                <!-- Additional charity-specific fields here -->
            </div>

            <!-- Common Fields -->
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" class="form-control">
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone_num" class="form-control">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required class="form-control">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required class="form-control">
            </div>

            <button type="submit" class="btn btn-primary btn-block">Register</button>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/
