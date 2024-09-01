<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registracija</title>
</head>
<body>
    <h2>Registracija</h2>
    <form action="${pageContext.request.contextPath}/user/register" method="post">
        <label for="firstName">Ime:</label>
        <input type="text" id="firstName" name="firstName" required><br>
        <label for="lastName">Prezime:</label>
        <input type="text" id="lastName" name="lastName" required><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        <label for="password">Lozinka:</label>
        <input type="password" id="password" name="password" required><br>
        <label for="confirmPassword">Potvrdi lozinku:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required><br>
        <button type="submit">Registruj se</button>
    </form>
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
</body>
</html>
