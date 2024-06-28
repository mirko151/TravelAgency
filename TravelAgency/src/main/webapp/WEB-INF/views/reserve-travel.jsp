<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reserve Travel</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Reserve Travel</h1>
        <form action="${pageContext.request.contextPath}/reservation/add" method="post">
            <input type="hidden" name="travel.id" value="${reservation.travel.id}" />
            <label for="destinationName">Destination Name:</label>
            <input type="text" id="destinationName" name="destinationName" value="${reservation.travel.destinationName}" readonly /><br/>
            
            <label for="departureDate">Departure Date:</label>
            <input type="datetime-local" id="departureDate" name="departureDate" value="${reservation.travel.departureDate}" readonly /><br/>
            
            <label for="returnDate">Return Date:</label>
            <input type="datetime-local" id="returnDate" name="returnDate" value="${reservation.travel.returnDate}" readonly /><br/>
            
            <label for="passengers">Number of Passengers:</label>
            <input type="number" id="passengers" name="passengers" required /><br/>
            
            <button type="submit">Reserve</button>
        </form>
    </div>
</body>
</html>
