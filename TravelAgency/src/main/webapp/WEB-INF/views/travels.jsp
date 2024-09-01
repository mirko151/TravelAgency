<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Putovanja</title>
</head>
<body>
    <h2>Putovanja</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Destinacija</th>
                <th>Prevoz</th>
                <th>Smeštaj</th>
                <th>Datum polaska</th>
                <th>Cena</th>
                <th>Akcija</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="travel" items="${travels}">
                <tr>
                    <td>${travel.destinationName}</td>
                    <td>${travel.transportMode}</td>
                    <td>${travel.accommodation}</td>
                    <td>${travel.departureDate}</td>
                    <td>${travel.price}</td>
                    <td><a href="${pageContext.request.contextPath}/reservation/create/${travel.id}">Rezerviši</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
