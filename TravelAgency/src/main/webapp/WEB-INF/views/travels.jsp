<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Travels</title>
</head>
<body>
    <h2>Available Travels</h2>
    <table>
        <tr>
            <th>Destination</th>
            <th>Price</th>
            <th>Available Seats</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="travel" items="${travels}">
            <tr>
                <td>${travel.destinationName}</td>
                <td>${travel.currentPrice}</td>
                <td>${travel.availableSeats}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/travel/edit/${travel.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/travel/delete/${travel.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/travel/add">Add New Travel</a>
</body>
</html>
