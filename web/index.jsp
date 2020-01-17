<%--
  Created by IntelliJ IDEA.
  User: Patryk
  Date: 16.01.2020
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Chatroom | Talk with everyone</title>

    <link href="dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
</head>

<body>

<jsp:include page="include/navbar.jsp"/>


<%--<div class="d-flex flex-column flex-md-row align-items-center p-1 px-md-4 mb-3 bg-white border-bottom box-shadow">--%>
    <%--<h5 class="my-0 mr-md-auto font-weight-normal">Chatroom</h5>--%>
    <%--<a class="btn btn-outline-primary mr-2" href="#">Zaloguj się</a>--%>
    <%--<a class="btn btn-outline-primary" href="#">Zarejestruj się</a>--%>
<%--</div>--%>

<div class="sortDiv">
    Sortowanie:
    <select name="selectSort">
        <option value="new">Najnowsze</option>
        <option value="old">Najstarsze</option>
    </select>
</div>
<div class="container">
    <div class="px-4 py-4 chat-box bg-white">

        <c:forEach items="${listPost}" var="post">
            <div class="card mb-4">
                <div class="card-header">
                    Użytkownik <b>${post.userByIdUser.username}
                </b> napisał dnia <i>
                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${post.date}"/>
                </i>:
                </div>
                <div class="card-body">
                    <p class="card-text">${post.text}</p>
                </div>
            </div>
        </c:forEach>

    </div>

    <!-- Typing area -->
    <form action="#" class="bg-light">
        <div class="input-group">
            <textarea cols="115" name="textareaMessage" placeholder="Napisz wiadomość..."></textarea>
            <%--<input type="text" placeholder="Type a message" aria-describedby="button-addon2"--%>
            <%--class="form-control rounded-0 border-0 py-4 bg-light">--%>
            <div class="input-group-append">
                <input type="submit" name="buttonSendMessage" class="btn btn-primary" value="Wyślij"/>
                <%--<button id="button-addon2" type="submit" class="btn btn-link"><i class="fa fa-paper-plane"></i>--%>
                <%--</button>--%>
            </div>
        </div>
    </form>
</div>
<hr class="mt-1">

<div class="container">
    <footer class="page-footer font-small stylish-color-dark ">
        <div class="footer-copyright text-center mb-3">© <?php echo date("Y"); ?> Copyright:
            <a href="https://github.com/toczak"> Patryk Potoczak</a>. Wszelkie prawa zastrzeżone.
        </div>
    </footer>
</div>

${infoRegister}


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="assets/js/vendor/popper.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<script src="assets/js/vendor/holder.min.js"></script>
<script>
    Holder.addTheme('thumb', {
        bg: '#55595c',
        fg: '#eceeef',
        text: 'Thumbnail'
    });
</script>


</body>
