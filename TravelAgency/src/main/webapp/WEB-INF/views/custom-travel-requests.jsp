<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Travel Agency - Custom Travel Requests</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <header>
        <h1>Custom Travel Requests</h1>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/travel/all">All Travels</a></li>
                <li><a href="${pageContext.request.contextPath}/user/profile">Profile</a></li>
                <li><a href="${pageContext.request.contextPath}/user/logout">Logout</a></li>
            </ul>
        </nav>
    </header>

    <section>
        <h2>Pending Requests</h2>
        <ul>
            <c:forEach var="request" items="${requests}">
                <li>
                    <p>Customer: ${request.user.firstName} ${request.user.lastName}</p>
                    <p>Destination: ${request.destination}</p>
                    <p>Transport: ${request.transportMode}</p>
                    <p>Number of People: ${request.numberOfPeople}</p>
                    <p>Travel Period: <fmt:formatDate value="${request.startDate}" pattern="dd.MM.yyyy"/> - <fmt:formatDate value="${request.endDate}" pattern="dd.MM.yyyy"/></p>
                    <form action="${pageContext.request.contextPath}/custom-travel/create-travel/${request.id}" method="post">
                        <button type="submit">Create Travel</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/custom-travel/reject-request/${request.id}" method="post">
                        <label for="comment">Reject Reason:</label>
                        <input type="text" name="comment" id="comment" required>
                        <button type="submit">Reject</button>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </section>

    <footer>
        <p>&copy; 2024 Travel Agency</p>
    </footer>
</body>
</html>
