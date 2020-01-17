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

    <title>Chatroom | Register</title>

    <link href="dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
</head>

<body class="text-center">
<jsp:include page="include/navbar.jsp"/>

<form class="form-signin" method="post" action="register">
    <h1 class="h3 mb-3 font-weight-normal">Register</h1>
    <input type="text" name="inputNick" class="form-control mb-2" placeholder="Login" required="" autofocus="" value="${name}">
    ${resultName}
    <input type="email" name="inputEmail" class="form-control mb-2" placeholder="Your e-mail" required="" value="${email}">
    ${resultEmail}
    <input type="password" name="inputPassword" class="form-control mb-2" placeholder="Password" required="">
    ${resultPassword}
    <input type="password" name="inputPassword2" class="form-control mb-2" placeholder="Repeat password" required="">
    ${resultPassword2}
    <input class="btn btn-lg btn-primary btn-block" value="Register" type="submit"/>
</form>

</body>
</html>