
<%@ page contentType="text/html charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html  lang="en">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add User</title>
</head>
<body>
<h1>Login to Your Account</h1>
<form method="post" action="/login-processing">
    <input type="text" name="username" />
    <input type="password" name="password" />
    <button type="submit">Login</button>
</form>
</body>
</html>