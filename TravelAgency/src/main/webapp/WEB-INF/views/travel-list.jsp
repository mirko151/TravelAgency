<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Travel List</title>
</head>
<body>
    <h1>Travel List</h1>
    <a href="${pageContext.request.contextPath}/travel/add">Add New Travel</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Destination</th>
            <th>Transport Mode</th>
            <th>Accommodation</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="travel" items="${travels}">
            <tr>
                <td>${travel.id}</td>
                <td>${travel.destinationName}</td>
                <td>${travel.transportMode}</td>
                <td>${travel.accommodation}</td>
                <td>${travel.price}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/travel/edit/${travel.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/travel/delete/${travel.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
