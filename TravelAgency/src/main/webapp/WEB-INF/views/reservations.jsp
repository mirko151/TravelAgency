<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Travel Agency - Reservations</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <header>
        <h1>Your Reservations</h1>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/travel/all">All Travels</a></li>
                <li><a href="${pageContext.request.contextPath}/user/logout">Logout</a></li>
            </ul>
        </nav>
    </header>

    <section>
        <h2>Upcoming Reservations</h2>
        <ul>
            <c:forEach var="reservation" items="${upcomingReservations}">
                <li>
                    <p>Destination: ${reservation.travel.destinationName}</p>
                    <p>Departure Date: <fmt:formatDate value="${reservation.travel.departureDate}" pattern="dd.MM.yyyy HH:mm"/></p>
                    <form action="${pageContext.request.contextPath}/user/profile/cancel-reservation/${reservation.id}" method="post">
                        <button type="submit">Cancel Reservation</button>
                    </form>
                </li>
            </c:forEach>
        </ul>

        <h2>Past Reservations</h2>
        <ul>
            <c:forEach var="reservation" items="${pastReservations}">
                <li>
                    <p>Destination: ${reservation.travel.destinationName}</p>
                    <p>Departure Date: <fmt:formatDate value="${reservation.travel.departureDate}" pattern="dd.MM.yyyy HH:mm"/></p>
                </li>
            </c:forEach>
        </ul>
    </section>

    <footer>
        <p>&copy; 2024 Travel Agency</p>
    </footer>
</body>
</html>
