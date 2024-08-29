<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Travel</title>
</head>
<body>
    <h2>Add New Travel</h2>
    <form action="${pageContext.request.contextPath}/travel/add" method="post" enctype="multipart/form-data">
        <label for="destinationName">Destination Name:</label>
        <input type="text" id="destinationName" name="destinationName" required>
        <br>
        <label for="transportMode">Transport Mode:</label>
        <select id="transportMode" name="transportMode">
            <c:forEach var="mode" items="${transportModes}">
                <option value="${mode}">${mode}</option>
            </c:forEach>
        </select>
        <br>
        <label for="accommodation">Accommodation:</label>
        <select id="accommodation" name="accommodation">
            <c:forEach var="acc" items="${accommodations}">
                <option value="${acc}">${acc}</option>
            </c:forEach>
        </select>
        <br>
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" required>
        <br>
        <label for="availableSeats">Available Seats:</label>
        <input type="number" id="availableSeats" name="availableSeats" required>
        <br>
        <label for="image">Upload Image:</label>
        <input type="file" id="image" name="image">
        <br>
        <button type="submit">Add Travel</button>
    </form>
</body>
</html>
