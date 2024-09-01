<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Travel Agency - Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <header>
        <h1>Error</h1>
    </header>

    <section>
        <p>An error occurred: ${error}</p>
        <a href="${pageContext.request.contextPath}/">Go back to home page</a>
    </section>

    <footer>
        <p>&copy; 2024 Travel Agency</p>
    </footer>
</body>
</html>
