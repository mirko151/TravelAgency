<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Greška</title>
</head>
<body>
    <h2>Došlo je do greške</h2>
    <p>${errorMessage}</p>
    <a href="${pageContext.request.contextPath}/">Vrati se na početnu stranicu</a>
</body>
</html>
