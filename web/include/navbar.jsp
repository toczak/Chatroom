<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Patryk
  Date: 17.01.2020
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div class="d-flex flex-column flex-md-row align-items-center p-1 px-md-4 mb-3 bg-white border-bottom box-shadow">

    <h5 class="my-0 mr-md-auto font-weight-normal"><a href="/index">Chatroom</a></h5>
    <c:choose>
        <c:when test="${sessionScope.user==null}">
            <a class="btn btn-outline-primary mr-2" href="/login">Sing in</a>
            <a class="btn btn-outline-primary" href="/register">Register</a>
        </c:when>
        <c:otherwise>
            <a class="btn btn-outline-primary" href="/logout">Logout</a>
        </c:otherwise>
    </c:choose>
</div>
</html>
