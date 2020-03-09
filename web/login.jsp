<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:if test="${sessionScope.login!=null}">
    <c:redirect url="index"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Chatroom | Login</title>

    <link href="dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
</head>

<body class="text-center">
<jsp:include page="include/navbar.jsp"/>

<form class="form-signin" method="post" action="login">
    <h1 class="h3 mb-3 font-weight-normal">Log In</h1>
    <input type="text" name="inputLogin" class="form-control" placeholder="Username / address e-mail" required=""
           autofocus="">
    <input type="password" name="inputPassword" class="form-control" placeholder="Password" required="">
    <input class="btn btn-lg btn-primary btn-block" value="Log in" type="submit">
    <a class="nav-link" href="register">You don't have an account? Please register.</a>
</form>

${infoLogin}
</body>
</html>