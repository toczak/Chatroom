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


<div class="sortDiv">
    <form method="post">
        Sorting:
        <select name="selectSort" onchange="executeSelect(this.form)">
            <option value="new" <c:if test="${sortByLatest==true}">selected</c:if>>Latest</option>
            <option value="old" <c:if test="${sortByLatest==false}">selected</c:if>>Oldest</option>
        </select>
    </form>
</div>

<div class="searchDiv">
    <form method="get" action="search">
        Search by username:
        <input type="text" name="username"/>
        <input type="submit" value="Search">
    </form>
</div>
<div class="container">
    <div class="px-4 py-4 chat-box bg-white">
        <c:forEach items="${listPost}" var="post">
            <div class="card mb-4">
                <div class="card-header
                <c:if test="${sessionScope.user.id==post.userByIdUser.id}">
                <c:out value="bg-primary"/>
                </c:if>"
                >
                    User <b>
                    <c:out value="${post.userByIdUser.username}"/>
                </b> wrote on <i>
                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${post.date}"/>
                </i>:
                </div>
                <div class="card-body">
                    <p class="card-text">
                            <c:out value="${post.text}"/></p>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="bg-white">
        <c:choose>
            <c:when test="${sessionScope.user!=null}">
                <form method="post" action="add">
                    <div class="input-group">
                        <textarea cols="115" name="textareaMessage" placeholder="Write message..." required></textarea>
                        <div class="input-group-append">
                            <input type="submit" name="buttonSendMessage" class="btn btn-primary" value="Send"/>
                        </div>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <div class="text-center"><p class="mt-2 h5 font-weight-bold">Please login to comment.</p></div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<hr class="mt-1">

<div class="container">
    <footer class="page-footer font-small stylish-color-dark ">
        <div class="footer-copyright text-center mb-3">© <?php echo date("Y"); ?> Copyright:
            <a href="https://github.com/toczak"> Patryk Potoczak</a>. All rights reserved.
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
    function executeSelect(form){
        form.submit();
    }
</script>
<script>
    Holder.addTheme('thumb', {
        bg: '#55595c',
        fg: '#eceeef',
        text: 'Thumbnail'
    });
</script>


</body>
