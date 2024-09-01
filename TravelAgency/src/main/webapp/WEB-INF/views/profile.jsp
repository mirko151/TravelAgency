<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Travel Agency - Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <header>
        <h1>User Profile</h1>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/travel/all">All Travels</a></li>
                <li><a href="${pageContext.request.contextPath}/user/logout">Logout</a></li>
            </ul>
        </nav>
    </header>

    <section>
        <h2>Profile Details</h2>
        <form action="${pageContext.request.contextPath}/user/profile/update" method="post">
            <label for="firstName">First Name:</label>
            <input type="text" name="firstName" id="firstName" value="${user.firstName}" required>

            <label for="lastName">Last Name:</label>
            <input type="text" name="lastName" id="lastName" value="${user.lastName}" required>

            <label for="dateOfBirth">Date of Birth:</label>
            <input type="date" name="dateOfBirth" id="dateOfBirth" value="${user.dateOfBirth}" required>

            <label for="jmbg">JMBG:</label>
            <input type="text" name="jmbg" id="jmbg" value="${user.jmbg}" required>

            <label for="address">Address:</label>
            <input type="text" name="address" id="address" value="${user.address}">

            <label for="phoneNumber">Phone Number:</label>
            <input type="text" name="phoneNumber" id="phoneNumber" value="${user.phoneNumber}">

            <button type="submit">Update Profile</button>
        </form>

        <h2>Change Password</h2>
        <form action="${pageContext.request.contextPath}/user/profile/change-password" method="post">
            <label for="oldPassword">Old Password:</label>
            <input type="password" name="oldPassword" id="oldPassword" required>

            <label for="newPassword">New Password:</label>
            <input type="password" name="newPassword" id="newPassword" required>

            <label for="confirmPassword">Confirm New Password:</label>
            <input type="password" name="confirmPassword" id="confirmPassword" required>

            <button type="submit">Change Password</button>
        </form>

        <c:if test="${not empty success}">
            <p style="color: green;">${success}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
    </section>

    <section>
        <h2>Your Reservations</h2>
        <h3>Upcoming Reservations</h3>
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

        <h3>Past Reservations</h3>
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
