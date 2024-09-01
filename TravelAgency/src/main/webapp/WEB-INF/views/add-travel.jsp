<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dodaj putovanje</title>
</head>
<body>
    <h2>Dodavanje novog putovanja</h2>
    <form action="${pageContext.request.contextPath}/travel/add" method="post" enctype="multipart/form-data">
        <label for="destinationName">Naziv destinacije:</label>
        <input type="text" id="destinationName" name="destinationName" required><br>
        <label for="transportMode">Prevozno sredstvo:</label>
        <select id="transportMode" name="transportMode">
            <option value="PLANE">Avion</option>
            <option value="BUS">Autobus</option>
            <option value="SELF_TRANSPORT">Sopstveni prevoz</option>
        </select><br>
        <label for="accommodation">Smeštaj:</label>
        <select id="accommodation" name="accommodation">
            <option value="HOTEL">Hotel</option>
            <option value="APARTMENT">Apartman</option>
        </select><br>
        <label for="departureDate">Datum polaska:</label>
        <input type="datetime-local" id="departureDate" name="departureDate" required><br>
        <label for="returnDate">Datum povratka:</label>
        <input type="datetime-local" id="returnDate" name="returnDate" required><br>
        <label for="numberOfNights">Broj noćenja:</label>
        <input type="number" id="numberOfNights" name="numberOfNights" required><br>
        <label for="price">Cena aranžmana:</label>
        <input type="number" id="price" name="price" step="0.01" required><br>
        <label for="totalSeats">Ukupan broj mesta:</label>
        <input type="number" id="totalSeats" name="totalSeats" required><br>
        <label for="image">Slika destinacije:</label>
        <input type="file" id="image" name="image"><br>
        <button type="submit">Dodaj putovanje</button>
    </form>
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
</body>
</html>
