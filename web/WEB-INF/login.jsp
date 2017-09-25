<%--
  Created by IntelliJ IDEA.
  User: Paweł
  Date: 2017-08-22
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Weekop logowanie</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>

<body>
<c:choose>
    <c:when test="${username eq 'admin'}">
        <jsp:include page="fragment/navbarAdmin.jspf"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="fragment/navbar.jspf"/>
    </c:otherwise>
</c:choose>

<div class="container">
    <br><br>
    <div class="col col-sm-6 col-md-4 col-md-offset-4">
    <form class="form-signin" action="j_security_check" method="post">
        <h2 class="form-signin-heading">Zaloguj się</h2>
        <input name="j_username" type="text" class="form-control" placeholder="Nazwa użytkownika" required autofocus>
        <label class="sr-only">Hasło:</label>
        <input name="j_password" type="password" class="form-control" placeholder="Hasło" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Zaloguj</button>
       <a href="${pageContext.request.contextPath}/register" class="btn btn-danger btn-block">Zarejestruj</a>
    </form>

    </div>
</div> <!-- /container -->

<jsp:include page="fragment/footer.jspf"/>

<script src="http://code.jquery.com/jquery-1.11.2.min.js" ></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js" ></script>
<script src="../resources/css/bootstrap.min.css"></script>
</body>
</html>
