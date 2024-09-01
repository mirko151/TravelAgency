<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Travel Agency - Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <header>
        <h1>Register</h1>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/user/login">Login</a></li>
            </ul>
        </nav>
    </header>

    <section>
        <form action="${pageContext.request.contextPath}/user/register" method="post">
            <label for="firstName">First Name:</label>
            <input type="text" name="firstName" id="firstName" required>

            <label for="lastName">Last Name:</label>
            <input type="text" name="lastName" id="lastName" required>

            <label for="dateOfBirth">Date of Birth:</label>
            <input type="date" name="dateOfBirth" id="dateOfBirth" required>

            <label for="jmbg">JMBG:</label>
            <input type="text" name="jmbg" id="jmbg" required>

            <label for="address">Address:</label>
            <input type="text" name="address" id="address">

            <label for="phoneNumber">Phone Number:</label>
            <input type="text" name="phoneNumber" id="phoneNumber">

            <label for="email">Email:</label>
            <input type="email" name="email" id="email" required>

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required>

            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" name="confirmPassword" id="confirmPassword" required>

            <button type="submit">Register</button>
        </form>

        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
    </section>

    <footer>
        <p>&copy; 2024 Travel Agency</p>
    </footer>
</body>
</html>
