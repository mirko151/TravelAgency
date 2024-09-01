<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Prijava</title>
</head>
<body>
    <h2>Prijava</h2>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required><br>
        <label for="password">Lozinka:</label>
        <input type="password" id="password" name="password" required><br>
        <button type="submit">Prijavi se</button>
    </form>
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
</body>
</html>
