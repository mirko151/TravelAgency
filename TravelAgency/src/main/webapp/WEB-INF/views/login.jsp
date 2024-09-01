<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Travel Agency - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <header>
        <h1>Login</h1>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/user/register">Register</a></li>
            </ul>
        </nav>
    </header>

    <section>
        <form action="${pageContext.request.contextPath}/user/login" method="post">
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" required>

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required>

            <button type="submit">Login</button>
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
