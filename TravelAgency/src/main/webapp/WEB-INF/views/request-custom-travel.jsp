<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Request Custom Travel</title>
</head>
<body>
    <h2>Request Custom Travel</h2>
    <form action="${pageContext.request.contextPath}/custom-travel/request" method="post">
        <label for="destination">Destination:</label>
        <input type="text" id="destination" name="destination" required><br>
        <label for="transportMode">Transport Mode:</label>
        <select id="transportMode" name="transportMode">
            <option value="PLANE">Plane</option>
            <option value="BUS">Bus</option>
            <option value="SELF_TRANSPORT">Self Transport</option>
        </select><br>
        <label for="numberOfPeople">Number of People:</label>
        <input type="number" id="numberOfPeople" name="numberOfPeople" required><br>
        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" required><br>
        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" required><br>
        <button type="submit">Submit Request</button>
    </form>
</body>
</html>
