<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Travel Form</title>
</head>
<body>
    <h1>Travel Form</h1>
    <form action="${pageContext.request.contextPath}/travel/${travel.id == 0 ? 'add' : 'edit/' + travel.id}" method="post">
        <input type="hidden" name="id" value="${travel.id}" />
        <label for="destinationName">Destination Name:</label>
        <input type="text" id="destinationName" name="destinationName" value="${travel.destinationName}" required /><br/>
        <label for="transportMode">Transport Mode:</label>
        <select id="transportMode" name="transportMode">
            <c:forEach var="mode" items="${transportModes}">
                <option value="${mode}" ${mode == travel.transportMode ? 'selected' : ''}>${mode}</option>
            </c:forEach>
        </select><br/>
        <label for="accommodation">Accommodation:</label>
        <select id="accommodation" name="accommodation">
            <c:forEach var="accommodation" items="${accommodations}">
                <option value="${accommodation}" ${accommodation == travel.accommodation ? 'selected' : ''}>${accommodation}</option>
            </c:forEach>
        </select><br/>
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" value="${travel.price}" required /><br/>
        <label for="departureDate">Departure Date:</label>
        <input type="datetime-local" id="departureDate" name="departureDate" value="${travel.departureDate}" required /><br/>
        <label for="returnDate">Return Date:</label>
        <input type="datetime-local" id="returnDate" name="returnDate" value="${travel.returnDate}" required /><br/>
        <label for="nights">Nights:</label>
        <input type="number" id="nights" name="nights" value="${travel.nights}" required /><br/>
        <label for="totalSeats">Total Seats:</label>
        <input type="number" id="totalSeats" name="totalSeats" value="${travel.totalSeats}" required /><br/>
        <label for="availableSeats">Available Seats:</label>
        <input type="number" id="availableSeats" name="availableSeats" value="${travel.availableSeats}" required /><br/>
        <button type="submit">Save</button>
    </form>
</body>
</html>
