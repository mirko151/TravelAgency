<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Travel Agency - Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <header>
        <h1>Welcome to Our Travel Agency</h1>
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
        <h2>Available Travel Packages</h2>
        <c:choose>
            <c:when test="${not empty travels}">
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
            </c:when>
            <c:otherwise>
                <p>No travel packages available at the moment.</p>
            </c:otherwise>
        </c:choose>
    </section>

    <footer>
        <p>&copy; 2024 Travel Agency</p>
    </footer>
</body>
</html>
