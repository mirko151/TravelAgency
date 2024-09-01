<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Travel Agency - Travel Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <header>
        <h1>Travel Details</h1>
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
        <h2>${travel.destinationName}</h2>
        <img src="${travel.imagePath}" alt="${travel.destinationName}" width="400" height="300"/>
        <p>Price: <fmt:formatNumber value="${travel.price}" type="currency"/></p>
        <c:if test="${not empty travel.discountPrice}">
            <p><strong>Discounted Price: <fmt:formatNumber value="${travel.discountPrice}" type="currency"/></strong> 
            until <fmt:formatDate value="${travel.discountValidUntil}" pattern="dd.MM.yyyy"/></p>
        </c:if>
        <p>Transport: ${travel.transportMode}</p>
        <p>Accommodation: ${travel.accommodation}</p>
        <p>Departure Date: <fmt:formatDate value="${travel.departureDate}" pattern="dd.MM.yyyy HH:mm"/></p>
        <p>Return Date: <fmt:formatDate value="${travel.returnDate}" pattern="dd.MM.yyyy HH:mm"/></p>
        <p>Number of Nights: ${travel.numberOfNights}</p>
        <p>Total Seats: ${travel.totalSeats}</p>
        <p>Available Seats: ${travel.availableSeats}</p>

        <c:if test="${not empty loggedUser && loggedUser.role == 'CUSTOMER'}">
            <form action="${pageContext.request.contextPath}/travel/reserve/${travel.id}" method="post">
                <label for="numTravelers">Number of Travelers:</label>
                <input type="number" name="numTravelers" id="numTravelers" min="1" max="${travel.availableSeats}" required>
                <button type="submit">Reserve</button>
            </form>
        </c:if>

        <c:if test="${empty loggedUser}">
            <p><a href="${pageContext.request.contextPath}/user/login">Login</a> to reserve this travel package.</p>
        </c:if>
    </section>

    <footer>
        <p>&copy; 2024 Travel Agency</p>
    </footer>
</body>
</html>
