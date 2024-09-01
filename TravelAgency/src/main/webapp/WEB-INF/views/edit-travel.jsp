<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Travel Agency - ${pageTitle}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <header>
        <h1>${pageTitle}</h1>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/travel/all">All Travels</a></li>
                <c:if test="${not empty loggedUser}">
                    <li><a href="${pageContext.request.contextPath}/user/profile">Profile</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/logout">Logout</a></li>
                </c:if>
                <c:if test="${empty loggedUser}">
                    <li><a href="${pageContext.request.contextPath}/user/login">Login</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/register">Register</a></li>
                </c:if>
            </ul>
        </nav>
    </header>

    <section>
        <form action="${pageContext.request.contextPath}/travel/${formAction}" method="post" enctype="multipart/form-data">
            <label for="destinationName">Destination Name:</label>
            <input type="text" name="destinationName" id="destinationName" value="${travel.destinationName}" required>

            <label for="transportMode">Transport Mode:</label>
            <select name="transportMode" id="transportMode" required>
                <c:forEach var="mode" items="${transportModes}">
                    <option value="${mode}" <c:if test="${mode == travel.transportMode}">selected</c:if>>${mode}</option>
                </c:forEach>
            </select>

            <label for="accommodation">Accommodation:</label>
            <select name="accommodation" id="accommodation" required>
                <c:forEach var="accommodationType" items="${accommodations}">
                    <option value="${accommodationType}" <c:if test="${accommodationType == travel.accommodation}">selected</c:if>>${accommodationType}</option>
                </c:forEach>
            </select>

            <label for="departureDate">Departure Date:</label>
            <input type="datetime-local" name="departureDate" id="departureDate" value="${travel.departureDate}" required>

            <label for="returnDate">Return Date:</label>
            <input type="datetime-local" name="returnDate" id="returnDate" value="${travel.returnDate}" required>

            <label for="numberOfNights">Number of Nights:</label>
            <input type="number" name="numberOfNights" id="numberOfNights" value="${travel.numberOfNights}" required>

            <label for="price">Price:</label>
            <input type="number" name="price" id="price" step="0.01" value="${travel.price}" required>

            <label for="totalSeats">Total Seats:</label>
            <input type="number" name="totalSeats" id="totalSeats" value="${travel.totalSeats}" required>

            <label for="availableSeats">Available Seats:</label>
            <input type="number" name="availableSeats" id="availableSeats" value="${travel.availableSeats}" required>

            <label for="image">Travel Image:</label>
            <input type="file" name="image" id="image" accept="image/*">
            
            <c:if test="${not empty travel.imagePath}">
                <img src="${travel.imagePath}" alt="Travel Image" width="200" height="150"/>
            </c:if>

            <button type="submit">${formButtonLabel}</button>
        </form>
    </section>

    <footer>
        <p>&copy; 2024 Travel Agency</p>
    </footer>
</body>
</html>
