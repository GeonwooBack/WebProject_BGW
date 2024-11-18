<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="LoginController" method="post">
        <label for="user_id">User ID:</label>
        <input type="text" id="user_id" name="user_id" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        <button type="submit">Login</button>
    </form>
    <% if (request.getParameter("error") != null) { %>
        <p style="color:red;">Invalid username or password</p>
    <% } %>
</body>
</html>
