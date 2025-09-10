
<%@ page contentType="text/html charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html  lang="en">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
</head>
<body>
<h1>Login to Your Account</h1>
<form action="/perform-login" method="post" >
<div>
    <input type="text" name="username" placeholder="Enter your username"/>
    </div>
    <div>
    <input type="password" name="password" placeholder="Enter your password"/>
    </div>
    <div>
    <button type="submit">Login</button>
    </div>
</form>

    <hr>

    <!-- OAuth2 login -->
    <a href="/oauth2/authorization/google">
        <button>Login with Google</button>
    </a>
</body>
</html>