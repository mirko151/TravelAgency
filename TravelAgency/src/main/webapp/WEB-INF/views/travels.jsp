<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Travel Agency - Travels</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <header>
        <h1>Travel Packages</h1>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
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
        <h2>Filter and Sort Options</h2>
        <form action="${pageContext.request.contextPath}/travel/search" method="get">
            <label for="transportMode">Transport Mode:</label>
            <select name="transportMode" id="transportMode">
                <option value="">All</option>
                <c:forEach var="mode" items="${transportModes}">
                    <option value="${mode}">${mode}</option>
                </c:forEach>
            </select>

            <label for="minPrice">Min Price:</label>
            <input type="number" name="minPrice" id="minPrice" step="0.01" min="0">

            <label for="maxPrice">Max Price:</label>
            <input type="number" name="maxPrice" id="maxPrice" step="0.01" min="0">

            <label for="nights">Number of Nights:</label>
            <input type="number" name="nights" id="nights" min="1">

            <button type="submit">Search</button>
        </form>

        <h2>Available Travel Packages</h2>
        <ul>
            <c:forEach var="travel" items="${travels}">
                <li>
                    <img src="${travel.imagePath}" alt="${travel.destinationName}" width="200" height="150"/>
                    <h3>${travel.destinationName}</h3>
                    <p>Price: <fmt:formatNumber value="${travel.price}" type="currency"/></p>
                    <c:if test="${not empty travel.discountPrice}">
                        <p><strong>Discounted Price: <fmt:formatNumber value="${travel.discountPrice}" type="currency"/></strong> 
                        until <fmt:formatDate value="${travel.discountValidUntil}" pattern="dd.MM.yyyy"/></p>
                    </c:if>
                    <p>Transport: ${travel.transportMode}</p>
                    <p>Accommodation: ${travel.accommodation}</p>
                    <a href="${pageContext.request.contextPath}/travel/details/${travel.id}">View Details</a>
                </li>
            </c:forEach>
        </ul>
    </section>

    <footer>
        <p>&copy; 2024 Travel Agency</p>
    </footer>
</body>
</html>
