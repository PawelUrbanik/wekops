<%--
  Created by IntelliJ IDEA.
  User: Paweł
  Date: 2017-08-23
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dodaj znalezisko</title>
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
    <div class="<col-md-8 col-md-offset-2>">
        <form class="form-signin" action="/add" method="post">
            <h2 class="form-signin-heading">Dodaj nowe znalezisko</h2>
            <input name="inputName" type="text" class="form-control" placeholder="Nazwa znaleziska" required autofocus>
            <input name="inputUrl" type="url" class="form-control" placeholder="Adres URL" required autofocus>
            <textarea name="inputDescription" rows="5" class="form-control" placeholder="Opis" required autofocus></textarea>
            <input class="btn btn-primary btn-block" type="submit" value="Dodaj!">
        </form>
    </div>
</div> <!-- /container -->

<jsp:include page="fragment/footer.jspf" />

<script src="http://code.jquery.com/jquery-1.11.2.min.js" ></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js" ></script>
<script src="../resources/css/bootstrap.min.css"></script>
</body>
</html>
