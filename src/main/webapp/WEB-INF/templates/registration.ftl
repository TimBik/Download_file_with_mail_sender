<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
</head>

<body>


<form name="reg" modelAttribute="userObj" action="/registration" method="post">
    <div>
        <input type="text" id="user_name" name="user_name" placeholder="Type your nick" required>
    </div>

    <div>

        <input type="password" id="password" name="password" placeholder="Type your password" required>
    </div>
    <div>

        <input type="text" id="mail" name="mail" placeholder="Type your email" required/>
    </div>
    <div>
        <input type="text" id="login" name="login" placeholder="Type your login" required>
    </div>
    <div>

        <input type="date" id="date" name="birth">
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit" class="btn">Sign up</button>
</form>

</body>
</html>

