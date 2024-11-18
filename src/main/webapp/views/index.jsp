<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
    <h1>Welcome to WebProject_ABC</h1>
    <% if (session.getAttribute("userId") != null) { %>
        <p>Hello, <%= session.getAttribute("userId") %>!</p>
        <a href="logout.jsp">Logout</a>
    <% } else { %>
        <a href="login.jsp">Login</a>
    <% } %>
</body>
</html>
