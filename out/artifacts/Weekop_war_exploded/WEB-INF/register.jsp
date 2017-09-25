<%--
  Created by IntelliJ IDEA.
  User: Paweł
  Date: 2017-08-22
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Weekop rejestracja</title>
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
        <form class="form-signin" action="register" method="post">
            <h2 class="form-signin-heading">Zarejestruj się</h2>
            <input name="inputEmail" type="email" class="form-control" placeholder="Email" required autofocus>
            <input name="inputUsername" type="text" class="form-control" placeholder="Nazwa użytkownika" required>
            <input name="inputPassword" type="password" class="form-control" placeholder="Hasło" required>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Zarejestruj</button>
        </form>
    </div>
</div> <!-- /container -->
<jsp:include page="fragment/footer.jspf" />

<script src="http://code.jquery.com/jquery-1.11.2.min.js" ></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js" ></script>
<script src="../resources/css/bootstrap.min.css"></script>
</body>
</html>
