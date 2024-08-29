<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>
        <button type="submit">Login</button>
    </form>
    <a href="${pageContext.request.contextPath}/user/register">Register</a>
</body>
</html>
